package com.insider.base;

import com.insider.driver.CoreDriver;
import com.insider.driver.Driver;
import com.insider.listeners.TestListener;
import com.insider.pages.BasePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import static com.insider.utils.Config.*;

@Listeners(TestListener.class)
public class BaseTest {

    public Driver driver;

    public static BasePage basePage;
    protected static final String URL = getProperty("baseUrl");
    protected static final String BROWSER = getBrowser();
    protected static final int waitTimeDuration = Integer.parseInt(getProperty("waitTimeDuration"));

    @BeforeMethod
    public void testInit() {
        driver = new CoreDriver();
        driver.start(BROWSER, waitTimeDuration);
        basePage = new BasePage(driver);
        driver.goToUrl(URL);
        basePage.acceptAllCookies();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
