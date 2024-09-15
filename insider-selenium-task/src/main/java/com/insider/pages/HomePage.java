package com.insider.pages;

import com.insider.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class HomePage extends BasePage {

    private final By navigationBar = By.cssSelector("#navbarNavDropdown");
    private final By navBarDropdown = By.cssSelector("[data-toggle='dropdown']");
    private final By careersDropdownSub = By.cssSelector("[href$='/careers/']");
    private final By dropdownSub = By.cssSelector("[class='dropdown-sub']");

    public HomePage(Driver driver) {
        super(driver);
    }

    public HomePage checkHomePageOpened() {
        Assert.assertTrue(driver.isElementVisible(navigationBar));
        return new HomePage(driver);
    }

    public HomePage clickDropdownOnNavBar(String dropdownName) {
        WebElement element = getElementWithTextInList(driver.findElements(navBarDropdown), dropdownName);
        element.click();
        Assert.assertTrue(driver.isElementVisible(careersDropdownSub));
        return new HomePage(driver);
    }

    public CareersPage clickDropdownSubOnSubs(String subName) {
        WebElement element = getElementWithTextInList(driver.findElements(dropdownSub), subName);
        element.click();
        return new CareersPage(driver);
    }
}
