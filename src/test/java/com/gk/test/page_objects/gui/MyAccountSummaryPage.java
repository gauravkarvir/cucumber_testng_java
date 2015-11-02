package com.gk.test.page_objects.gui;

import com.gk.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Sample page object class which defines all the elements for a specific page.
 * Every  page object class should extend "PageObject" class
 */


public class MyAccountSummaryPage extends PageObject {


    private By myaccountSummaryTitle = By.cssSelector(".my_account h1");

    private By personalInformation = By.cssSelector(".label + .info_content");

    public String getMyaccountSummaryTitle() {
        return waitForExpectedElement(myaccountSummaryTitle).getText();
    }

    public List<WebElement> getPersonalInformation() {
        return getWebDriver().findElements(personalInformation);
    }


}
