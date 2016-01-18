package com.gk.test.step_definitions.gui.register;

import com.gk.test.framework.helpers.Props;
import com.gk.test.page_objects.gui.SignInOrRegisterPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;


public class SignInOrRegisterSteps {

    private SignInOrRegisterPage signInOrRegisterPage;

    public SignInOrRegisterSteps(SignInOrRegisterPage signInOrRegisterPage) {
        this.signInOrRegisterPage = signInOrRegisterPage;
    }


    @And("^i click on Register for New Registration$")
    public void i_click_on_Register_for_New_Registration() throws Throwable {
        signInOrRegisterPage.clickRegisterButton();
    }

    @Then("^i am signed out successfully and can view Sign In Page$")
    public void i_am_signed_out_successfully_and_can_view_Sign_In_Page() throws Throwable {
        assertThat(signInOrRegisterPage.getSignInOrRegisterTitle()).contains(Props.getMessage("signInTitle"));
    }


    @Given("^i enter loginId \"(.*?)\" and password \"(.*?)\"$")
    public void i_enter_loginId_and_password(String loginId, String password) throws Throwable {
        signInOrRegisterPage.loginIdText().clear();
        signInOrRegisterPage.loginIdText().sendKeys(loginId);

        signInOrRegisterPage.passwordText().clear();
        signInOrRegisterPage.passwordText().sendKeys(password);

    }


    @Then("^i can see the validation message \"(.*?)\"$")
    public void i_can_see_the_validation_message(String expectedErrorMessage) throws Throwable {
        assertThat(signInOrRegisterPage.getErrorMessage()).contains(Props.getMessage(expectedErrorMessage));
    }

    @And("^i click on Sign In button on the Login Page$")
    public void i_click_on_Sign_In_button_on_the_Login_Page() throws Throwable {
        signInOrRegisterPage.clickSignInButton();
    }


}
