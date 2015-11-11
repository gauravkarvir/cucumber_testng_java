package com.gk.test.step_definitions.gui;


import com.gk.test.framework.helpers.UrlBuilder;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class NavigationSteps {


    @Given("^i navigate to the Lloydspharmacy \"(.*?)\" page$")
    public void i_navigate_to_the_Lloydspharmacy_page(String pageName) throws Throwable {
        if (pageName.equals("HOME")) {
            UrlBuilder.startAtHomePage();
        }
    }


}