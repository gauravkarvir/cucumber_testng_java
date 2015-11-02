package com.gk.test.step_definitions.gui;

import com.gk.test.page_objects.gui.MyAccountSummaryPage;
import com.gk.test.step_definitions.gui.register.NewRegistrationSteps;
import cucumber.api.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;


public class MyAccountSummarySteps {

    private NewRegistrationSteps newRegistrationSteps;
    private MyAccountSummaryPage myAccountSummaryPage;

    public MyAccountSummarySteps(MyAccountSummaryPage myAccountSummaryPage, NewRegistrationSteps newRegistrationSteps) {
        this.newRegistrationSteps = newRegistrationSteps;
        this.myAccountSummaryPage = myAccountSummaryPage;
    }

    @Then("^i am registered successfully and can view \"([^\"]*)\" page$")
    public void i_am_registered_successfully_and_can_view_page(String pageTitle) throws Throwable {
        assertThat(myAccountSummaryPage.getMyaccountSummaryTitle()).isEqualToIgnoringCase(pageTitle);
        String nameOfTheCustomer = myAccountSummaryPage.getPersonalInformation().get(0).getText();
        String address = myAccountSummaryPage.getPersonalInformation().get(1).getText();
        String townOrCity = myAccountSummaryPage.getPersonalInformation().get(2).getText();
        String emailAddress = myAccountSummaryPage.getPersonalInformation().get(3).getText();

        assertThat(nameOfTheCustomer).isEqualToIgnoringCase(newRegistrationSteps.getFirstNameData() + " " + newRegistrationSteps.getLastNameData());
        assertThat(address).isEqualToIgnoringCase(newRegistrationSteps.getAddress1Data());
        assertThat(townOrCity).isEqualToIgnoringCase(newRegistrationSteps.getTownOrCityData());
        assertThat(emailAddress).isEqualToIgnoringCase(newRegistrationSteps.getEmailAddressData());


    }

}
