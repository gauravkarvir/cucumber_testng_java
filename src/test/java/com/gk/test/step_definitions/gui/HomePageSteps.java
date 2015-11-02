package com.gk.test.step_definitions.gui;


import com.gk.test.page_objects.gui.HomePage;
import cucumber.api.java.en.Given;

public class HomePageSteps {

    private HomePage homePage;

    public HomePageSteps(HomePage homePage) {
        this.homePage = homePage;
    }


    @Given("^i click \"(.*?)\" on the Home Page$")
    public void i_click_on_the_Home_Page(String pageToNavigate) throws Throwable {
        if (pageToNavigate.equals("SIGN_IN")) {
            homePage.clickSignInLink();
        } else if (pageToNavigate.equals("SIGN_OUT")) {
            homePage.clickSignOutLink();
        }
    }

}