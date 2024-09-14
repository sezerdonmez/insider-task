package com.insider.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface Driver {

    void start(String browserName, int waitTimeDuration);
    void quit();
    void goToUrl(String url);
    String getTitle();
    void setResolution(Resolution resolution);
    void changeCurrentWindow();
    WebElement findElement(By locator);
    List<WebElement> findElements(By locator);
    void waitForAjax();
    void hoverToElement(WebElement element);
    void hoverElementWithPosition(WebElement element, int rightPixel, int downPixel);
    void highlightElement(WebElement element);
    void waitUntilElementVisible (By locator);
    void waitUntilElementExist(By locator);
    void waitUntilTextVisibleElement (By locator , String text);
    void wait(int milliSec);
    boolean isElementExist(By locator);
    boolean isElementVisible(By locator);
    void takeScreenshotAndSaveToFile(String testName);
}
