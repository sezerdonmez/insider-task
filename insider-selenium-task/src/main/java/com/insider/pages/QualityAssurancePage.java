package com.insider.pages;

import com.insider.driver.Driver;
import org.openqa.selenium.By;

public class QualityAssurancePage extends BasePage {
    private final String PATH = "/careers/quality-assurance/";
    private final By seaAllQAJobsButton = By.cssSelector("[href*='department=qualityassurance']");

    public QualityAssurancePage(Driver driver) {
        super(driver);
    }

    public QualityAssurancePage getQualityAssurancePage(String baseUrl) {
        driver.goToUrl(baseUrl + PATH);
        driver.isElementVisible(seaAllQAJobsButton);

        return new QualityAssurancePage(driver);
    }

    public OpenPositionsPage clickSeeAllQaJobsButton() {
        driver.findElement(seaAllQAJobsButton).click();

        return new OpenPositionsPage(driver);
    }
}
