package com.example

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import grails.plugin.geb.ContainerGebSpec

@Integration
@Rollback
class DemoSpec extends ContainerGebSpec {

    void "test homepage title success"() {
        when: "The home page is visited"
        go '/'

        then: "The title is correct"
        title == "Grails"
    }

    void "test homepage title failure"() {
        when: "The home page is visited"
        go '/'

        then: "The title is incorrect"
        title == "Invalid Title"
    }

    void "test navigation bar is present"() {
        when: "The home page is visited"
        go '/'

        then: "The navigation bar is displayed"
        $("nav.navbar").displayed
    }

    void "test accessing Grails Guides link in footer"() {
        when: "The Grails Guides link in the footer is clicked"
        go '/'
        $("a", href: "https://guides.grails.org").click()

        then: "The Grails Guides page is opened"
        // Assert redirected to the external link (adjust assertion for external navigation simulation)
        currentUrl.contains("guides.grails.org")
    }

    void "test footer elements visibility"() {
        when: "The home page is visited"
        go '/'

        then: "The footer with Grails Guides section is visible"
        $("div.footer").displayed
        $("a[href='https://guides.grails.org']").text() == "Grails Guides"

        and: "The Documentation section is also visible"
        $("a[href='https://docs.grails.org']").text() == "Documentation"
    }

    void "test spinner is hidden on load"() {
        when: "The home page is visited"
        go '/'

        then: "The spinner is not visible"
        !$("div#spinner").displayed
    }

    void "test failure: Non-existent nav link"() {
        when: "Trying to click a non-existent link in the navbar"
        go '/'
        $("a", text: "Non-Existent Link").click()

        then: "An error occurs"
        thrown(org.openqa.selenium.NoSuchElementException)
    }

    void "test navbar toggle button functionality"() {
        when: "The navigation bar toggle button is clicked"
        go '/'
        $("button.navbar-toggler").click()

        then: "The navigation menu expands"
        $("div#navbarContent").attr("aria-expanded") == "true"
    }

    void "test community Slack link in footer"() {
        when: "The Slack link in the footer is clicked"
        go '/'
        $("a", href: "https://slack.grails.org").click()

        then: "The Slack page opens"
        // Assert redirected to the external Slack page (simulation for navigation)
        currentUrl.contains("slack.grails.org")
    }

    void "test failure: Invalid text in footer"() {
        when: "The page footer is checked"
        go '/'

        then: "An intentionally wrong assertion fails"
        $("div.footer").text().contains("Invalid Footer Text")
    }
}