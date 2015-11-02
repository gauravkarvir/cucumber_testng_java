package com.gk.test.step_definitions.gui.register;

import com.gk.test.page_objects.gui.SignInOrRegisterPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;


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
        Assert.assertTrue(signInOrRegisterPage.getSignInOrRegisterTitle().contains("Sign in or Register for a Lloydspharmacy account"), "Sign In Or Register Title");
    }


    @Given("^i enter loginId \"(.*?)\" and password \"(.*?)\"$")
    public void i_enter_loginId_and_password(String loginId, String password) throws Throwable {
        signInOrRegisterPage.loginIdText().sendKeys("");
        signInOrRegisterPage.loginIdText().sendKeys(loginId);

        signInOrRegisterPage.passwordText().sendKeys("");
        signInOrRegisterPage.passwordText().sendKeys(password);

    }

    @When("^i click on Sign In$")
    public void i_click_on_Sign_In() throws Throwable {
        signInOrRegisterPage.clickSignInButton();
    }

    @Then("^i can see the validation message \"(.*?)\"$")
    public void i_can_see_the_validation_message(String expectedErrorMessage) throws Throwable {
        Assert.assertTrue(signInOrRegisterPage.getErrorMessage().contains(signInOrRegisterPage.getProps().getString(expectedErrorMessage)), "Verify Error Message");
    }
}
