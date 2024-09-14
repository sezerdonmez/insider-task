package com.insider.driver;

import com.insider.utils.LogUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.insider.driver.Browser.*;
import static java.lang.String.format;

public class CoreDriver implements Driver {
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    @Override
    public void start(String browserName, int waitTimeDuration) {
        if (browserName.equals(CHROME.getName())) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments("--disable-cookies");
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver(chromeOptions);
        } else if (browserName.equals(FIREFOX.getName())) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addPreference("dom.webnotifications.enabled", false);
            firefoxOptions.addPreference("dom.push.enabled", false);
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver(firefoxOptions);
        } else if (browserName.equals(EDGE.getName())) {
            WebDriverManager.edgedriver().setup();
            webDriver = new EdgeDriver();
        }
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(waitTimeDuration));
    }

    @Override
    public void quit() {
        webDriver.quit();
    }

    @Override
    public void goToUrl(String url) {
        webDriver.navigate().to(url);
    }

    @Override
    public String getTitle() {
        return webDriver.getTitle();
    }

    @Override
    public void setResolution(Resolution resolution) {
        webDriver.manage().window().setSize(new Dimension(resolution.getWidth(), resolution.getHeight()));
    }

    @Override
    public void changeCurrentWindow() {
         String currentWindow = webDriver.getWindowHandle();
         Set<String> windows = webDriver.getWindowHandles();

         for (String window : windows)
             if(!window.equals(currentWindow)) {
                 webDriver.switchTo().window(window);
                 LogUtils.logInfo(format("Switched to new page: %s", window));
                 break;
             }
    }

    @Override
    public WebElement findElement(By locator) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        LogUtils.logInfo(format("Found web element located by: %s", locator));
        return webDriver.findElement(locator);
    }

    @Override
    public List<WebElement> findElements(By locator) {
        webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        LogUtils.logInfo(format("Found web elements located by: %s", locator));
        return webDriver.findElements(locator);
    }

    @Override
    public void waitForAjax() {
        webDriverWait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    @Override
    public void hoverToElement(WebElement element) {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(element).perform();
        LogUtils.logInfo(format("Hovered to web element: %s", element));
        if (!element.isDisplayed()) LogUtils.logError("Hovered element is not displayed");
    }

    @Override
    public void hoverElementWithPosition(WebElement element, int rightPixel, int downPixel) {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(element, rightPixel, downPixel).perform();
        LogUtils.logInfo(format("Hovered to web element: %s with position: %d right - %d down pixels",
                element.getAttribute("class"), rightPixel, downPixel));
        if (!element.isDisplayed()) LogUtils.logError("Hovered element is not displayed");
    }

    @Override
    public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].style.background='yellow'", element);
    }

    @Override
    public void waitUntilElementVisible(By locator) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Override
    public void waitUntilElementExist(By locator) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    @Override
    public void waitUntilTextVisibleElement(By locator, String text) {
        webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    @Override
    public void wait(int sec) {
        try {
            Thread.sleep(sec * 100L);
            LogUtils.logInfo(format("Waited for %s desi sec.", sec));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isElementExist(By locator) {
        return webDriver.findElements(locator).size() > 0;
    }

    @Override
    public boolean isElementVisible(By locator) {
        return webDriver.findElement(locator).isDisplayed();
    }

    @Override
    public void takeScreenshotAndSaveToFile(String testName) {
        File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);

        try {
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            File destFile = new File(System.getProperty("user.dir") + "/src/test/resources/screenshots/" + testName + "_" + timestamp + ".png");
            FileHandler.copy(srcFile, destFile);
            LogUtils.logInfo(format("Screenshot saved at: %s", destFile.getAbsolutePath()));
        } catch (IOException e) {
            LogUtils.logError("Failed to save screenshot: " + e.getMessage());
        }
    }
}
