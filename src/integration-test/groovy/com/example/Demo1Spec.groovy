package com.example

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import grails.plugin.geb.ContainerGebSpec

@Integration
@Rollback
class Demo1Spec extends ContainerGebSpec {

    void "verify page title"() {
        when: "The background color is changed and the Selenium Test Page is visited"
        go 'https://automationintesting.com/selenium/testpage/'
        js.exec("document.body.style.backgroundColor = 'red'")

        then: "The page title is as expected"
        title == "Selenium Test Page | Automation in Testing"
    }

    void "select multiple options in continents dropdown"() {
        when: "The background color is changed and multiple continents are selected"
        go 'https://automationintesting.com/selenium/testpage/'
        js.exec("document.body.style.backgroundColor = 'blue'")
        def dropdown = $("#continent")
        dropdown.find("option", value: "asia").click()
        dropdown.find("option", value: "europe").click()

        then: "The options Asia and Europe are selected"
        dropdown.find("option", value: "asia").@selected == "true"
        dropdown.find("option", value: "europe").@selected == "true"
    }

    void "verify social media icons exist in footer"() {
        when: "The background color is changed and the page footer is examined for social media icons"
        go 'https://automationintesting.com/selenium/testpage/'
        js.exec("document.body.style.backgroundColor = 'green'")

        then: "Social media links for Twitter and LinkedIn exist"
        $("a[href*='twitter.com']").size() > 0
        $("a[href*='linkedin.com']").size() > 0
    }

    void "verify clicking submit button does nothing"() {
        when: "The background color is changed and the form submit button is clicked"
        go 'https://automationintesting.com/selenium/testpage/'
        js.exec("document.body.style.backgroundColor = 'orange'")
        $("#submitbutton").click()

        then: "The submit button doesn't perform any navigation or action"
        currentUrl.contains("selenium/testpage") // Still on the same page
    }

}