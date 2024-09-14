package com.insider.pages;

import com.insider.driver.Driver;
import org.openqa.selenium.By;
import org.testng.Assert;

public class JobsLeverPage extends BasePage {
    private final By jobDescription = By.cssSelector("[data-qa='job-description']");
    private final By jobTitle = By.cssSelector(".posting-headline h2");
    private final By jobDepartment = By.cssSelector(".department");
    private final By jobLocation = By.cssSelector(".location");

    public JobsLeverPage(Driver driver) {
        super(driver);
    }

    public JobsLeverPage checkJobsLeverPageOpened(String jobName, String locationName) {
        Assert.assertTrue(driver.findElement(jobDescription).isDisplayed());
        Assert.assertTrue(getElementText(driver.findElement(jobTitle)).contains(jobName));
        Assert.assertTrue(getElementText(driver.findElement(jobDepartment)).contains(jobName));
        Assert.assertEquals(getElementText(driver.findElement(jobLocation)), locationName);

        return new JobsLeverPage(driver);
    }
}
