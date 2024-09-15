package com.insider.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FirefoxDriver implements Driver {

    @Override
    public void start(int waitTimeDuration) {

    }

    @Override
    public void quit() {

    }

    @Override
    public void goToUrl(String url) {

    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void setResolution(Resolution resolution) {

    }

    @Override
    public void changeCurrentWindow() {

    }

    @Override
    public WebElement findElement(By locator) {
        return null;
    }

    @Override
    public List<WebElement> findElements(By locator) {
        return null;
    }

    @Override
    public void waitForAjax() {

    }

    @Override
    public void hoverToElement(WebElement element) {

    }

    @Override
    public void hoverElementWithPosition(WebElement element, int rightPixel, int downPixel) {

    }

    @Override
    public void highlightElement(WebElement element) {

    }

    @Override
    public void waitUntilElementVisible(By locator) {

    }

    @Override
    public void waitUntilElementNotVisible(By locator) {

    }

    @Override
    public void waitUntilElementExist(By locator) {

    }

    @Override
    public void waitUntilTextVisibleElement(By locator, String text) {

    }

    @Override
    public void waitUntilAllElementsTextDesired(By locator, String text) {

    }

    @Override
    public void waitUntilElementAttributeChange(By locator, String attributeName, String firstAttributeValue) {

    }

    @Override
    public void wait(int milliSec) {

    }

    @Override
    public boolean isElementExist(By locator) {
        return false;
    }

    @Override
    public boolean isElementVisible(By locator) {
        return false;
    }

    @Override
    public void takeScreenshotAndSaveToFile(String testName) {

    }
}
