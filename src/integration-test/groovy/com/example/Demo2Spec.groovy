package com.example

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import grails.plugin.geb.ContainerGebSpec

@Integration
@Rollback
class Demo2Spec extends ContainerGebSpec {

    void "select multiple options in continents dropdown"() {
        when: "The background color is changed and multiple continents are selected"
        go 'https://automationintesting.com/selenium/testpage/'
        js.exec("document.body.style.backgroundColor = 'blue'")
        Thread.sleep(500)
        def dropdown = $("#continent")
        dropdown.find("option", value: "asia").click()
        dropdown.find("option", value: "europe").click()

        then: "The options Asia and Europe are selected"
        dropdown.find("option", value: "asia").@selected == "true"
        dropdown.find("option", value: "europe").@selected == "true"
    }
}
