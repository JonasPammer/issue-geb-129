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

        Thread.sleep(2000)

        then: "The page title is as expected"
        title == "Selenium Test Page | Automation in Testing"
    }

}