package com.insider.pages;

import com.insider.driver.Driver;
import com.insider.utils.LogUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static java.lang.String.format;

public class BasePage {

    protected final Driver driver;

    private final By acceptCookiesButton = By.cssSelector("#wt-cli-accept-all-btn");
    private final By selectBoxOptions = By.cssSelector("[role='option']");

    public BasePage(Driver driver) {
        this.driver = driver;
    }

    public Boolean isSelected (By by) {
        return driver.findElement(by).isSelected();
    }

    public void clickElementBy (By by, boolean isWithHover, boolean isWithHighlight) {
        WebElement elementToClick = driver.findElement(by);
        if (isWithHover)
            driver.hoverToElement(elementToClick);
        if (isWithHighlight)
            driver.highlightElement(elementToClick);
        elementToClick.click();
    }

    public void acceptAllCookies() {
        driver.findElement(acceptCookiesButton).click();
        LogUtils.logInfo("All web page cookies accepted");
    }

    public void clickOnSelectBox(By by, String optionName) {
        driver.findElement(by).click();

        List<WebElement> optionElements = driver.findElements(selectBoxOptions);
        WebElement elementToCheck = getElementWithTextInList(optionElements, optionName);
        Assert.assertTrue(elementToCheck.isDisplayed(), "Should not see desired select box option in select box options");
        LogUtils.logInfo(format("Clicked on select box located by: %s", by));
    }

    public void clickSelectBoxOption(String optionName) {
        List<WebElement> optionElements = driver.findElements(selectBoxOptions);
        WebElement elementToCheck = getElementWithTextInList(optionElements, optionName);
        elementToCheck.click();
        LogUtils.logInfo(format("Clicked on select box option: %s", optionName));
    }

    public WebElement getElementWithTextInList (List<WebElement> elementList, final String text) {
        boolean isElementHere = false;
        int indexOfElement = 0;
        for (int i = 0; i < elementList.size(); i++) {
            if (elementList.get(i).getText().equals(text)) {
                indexOfElement = i;
                isElementHere = true;
                break;
            }
        }
        Assert.assertTrue(isElementHere, "Element not found with text: " + text);
        return elementList.get(indexOfElement);
    }

    public void waitForElementCountInListDesired(By by, int maxElementCount, int maxWaitTime) {
        boolean isElementCountDesired = false;

        for (int i = 0; i < maxWaitTime; i++) {
            List<WebElement> elements = driver.findElements(by);
            if (elements.size() < maxElementCount && elements.size() > 0) {
                isElementCountDesired = true;
                break;
            }
            driver.wait(3);
        }

        Assert.assertTrue(isElementCountDesired, "Element count not to be desired count after waiting");
    }

    public void typeTextToElement (By by, String text) {
        WebElement elementToTypeText = driver.findElement(by);
        elementToTypeText.clear();
        Assert.assertEquals("", elementToTypeText.getText(), "Element should not have any text.");
        elementToTypeText.sendKeys(text);
        Assert.assertEquals(text, elementToTypeText.getAttribute("value"), "Element should have text: " + text);
    }

    public void checkAllElementsTextsEquals(By by, String text) {
        List<WebElement> elements = driver.findElements(by);

        for (WebElement element : elements)
            Assert.assertEquals(getElementText(element), text);
    }

    public String getElementText (WebElement element) {
        String elementText = element.getText();
        LogUtils.logInfo(format("Got web element text element: %s - text: %s", element, elementText));
        return elementText;
    }

    public String getCssValue (By by, String cssProp) {
        WebElement element = driver.findElement(by);
        return element.getCssValue(cssProp);
    }

    public String getElementAttribute (By by, String attributeName) {
        WebElement element = driver.findElement(by);
        return element.getAttribute(attributeName);
    }

    public String getCurrentPageTitle () {
        return driver.getTitle();
    }

}
