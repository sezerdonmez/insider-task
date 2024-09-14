package com.insider.listeners;

import com.insider.base.BaseTest;
import com.insider.driver.Driver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.insider.utils.LogUtils.logError;
import static com.insider.utils.LogUtils.logInfo;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext iTestContext) {
        try {
            logInfo(iTestContext.getName() + "TESTS WILL START");
        } catch (ArrayIndexOutOfBoundsException e) {
            logError("Cannot started");
        }
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        try {
            logError("TEST STARTED: {}" + iTestResult.getName());
        } catch (ArrayIndexOutOfBoundsException e) {
            logError("Cannot log test name!");
        }
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logInfo("Test Finished Successfully: " + iTestResult.getName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        Driver driver = ((BaseTest) testClass).driver;

        String testName = iTestResult.getName();
        driver.takeScreenshotAndSaveToFile(testName);

        logError("Test Failed and saved screenshot");
    }
}
