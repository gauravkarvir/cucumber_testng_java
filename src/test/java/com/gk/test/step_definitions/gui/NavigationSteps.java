package com.gk.test.step_definitions.gui;


import com.gk.test.framework.helpers.UrlBuilder;
import cucumber.api.java.en.Given;


public class NavigationSteps {


    @Given("^i navigate to the website \"(.*?)\" page$")
    public void i_navigate_to_the_website_page(String pageName) throws Throwable {
        if (pageName.equals("HOME")) {
            UrlBuilder.startAtHomePage();
        }
    }


}