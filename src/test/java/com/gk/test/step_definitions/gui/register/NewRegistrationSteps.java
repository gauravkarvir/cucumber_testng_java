package com.gk.test.step_definitions.gui.register;


import com.gk.test.enums.PermittedCharacters;
import com.gk.test.framework.helpers.utils.RandomGenerator;
import com.gk.test.page_objects.gui.NewRegistrationPage;
import cucumber.api.java.en.When;
import lombok.Getter;

import static org.assertj.core.api.Assertions.assertThat;

@Getter
public class NewRegistrationSteps {

    private NewRegistrationPage newRegistrationPage;

    private String loginIdData = RandomGenerator.random(6, PermittedCharacters.ALPHANUMERIC);
    private String passwordData = RandomGenerator.random(6, PermittedCharacters.ALPHANUMERIC);
    private String titleData = "Dr.";
    private String firstNameData = RandomGenerator.random(6, PermittedCharacters.ALPHABETS);
    private String lastNameData = RandomGenerator.random(6, PermittedCharacters.ALPHABETS);
    private String postCodeData = "UB10 9DW";
    private String address1Data = RandomGenerator.random(6, PermittedCharacters.ALPHABETS);
    private String townOrCityData = RandomGenerator.random(6, PermittedCharacters.ALPHABETS);
    private String emailAddressData = RandomGenerator.randomEmailAddress(6);
    private String birthDateData = "1";
    private String birthMonthData = "1";
    private String birthYearData = "1982";


    public NewRegistrationSteps(NewRegistrationPage newRegistrationPage) {
        this.newRegistrationPage = newRegistrationPage;
    }


    @When("^i fill in the registration form on New Registration page$")
    public void i_fill_in_the_registration_form_on_New_Registration_page() throws Throwable {
        assertThat(newRegistrationPage.checkNewRegistrationForm()).isTrue();
        enterUserRegistrationDetails();

    }


    public void enterUserRegistrationDetails() {

        newRegistrationPage.loginIdText().sendKeys(loginIdData);
        newRegistrationPage.passwordText().sendKeys(passwordData);
        newRegistrationPage.verifyPasswordText().sendKeys(passwordData);

        newRegistrationPage.selectTitle().selectByVisibleText(titleData);
        newRegistrationPage.firstNameText().sendKeys(firstNameData);
        newRegistrationPage.lastNameText().sendKeys(lastNameData);

        newRegistrationPage.postCodeText().sendKeys(postCodeData);
        newRegistrationPage.address1Text().sendKeys(address1Data);
        newRegistrationPage.townOrCityText().sendKeys(townOrCityData);

        newRegistrationPage.emailAddressText().sendKeys(emailAddressData);
        newRegistrationPage.confirmEmailAddressText().sendKeys(emailAddressData);

        newRegistrationPage.birthDayText().selectByVisibleText(birthDateData);
        newRegistrationPage.birthMonthText().selectByVisibleText(birthMonthData);
        newRegistrationPage.birthYearText().selectByVisibleText(birthYearData);

        newRegistrationPage.acceptTermsAndConditions(true);

        newRegistrationPage.submitRegistration();

    }


}