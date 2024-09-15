package com.insider.base;

import com.insider.driver.*;
import com.insider.listeners.TestListener;
import com.insider.pages.BasePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.util.Locale;

import static com.insider.utils.Config.*;

@Listeners(TestListener.class)
public class BaseTest {

    public Driver driver;

    public static BasePage basePage;
    protected static final String URL = getProperty("baseUrl");
    protected static final int waitTimeDuration = Integer.parseInt(getProperty("waitTimeDuration"));

    @BeforeMethod
    public void testInit() {
        getDriver();
        driver.start(waitTimeDuration);
        basePage = new BasePage(driver);
        driver.goToUrl(URL);
        basePage.acceptAllCookies();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    private void getDriver() {
        String browserName = getBrowser();

        switch (browserName) {
            case "chrome" -> driver = new ChromeDriver();
            case "firefox" -> driver = new FirefoxDriver();
            default -> throw new IllegalArgumentException("Invalid browser name given: " + browserName);
        }
    }
}
