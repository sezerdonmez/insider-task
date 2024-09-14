package com.insider.pages;

import com.insider.driver.Driver;
import com.insider.enums.PageTitle;
import com.insider.helpers.StringHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class CareersPage extends BasePage {
    private final By mainTitle = By.cssSelector(".big-title");
    private final By categoryTitles = By.cssSelector(".category-title-media");
    private final By seeAllTeamsButton = By.cssSelector("[class*='loadmore']");
    private final By locationCityNames = By.cssSelector(".location-info p");
    private final By locationRightArrowIcon = By.cssSelector(".icon-arrow-right");
    private final By locationLeftArrowIcon = By.cssSelector(".icon-arrow-left");
    private final By locationProgressBar = By.cssSelector(".progress");
    private final By defaultSizeTitles = By.cssSelector(".elementor-size-default");
    private final By sliderActiveImage = By.cssSelector(".swiper-slide-active div");
    private final By sliderNextImage = By.cssSelector(".swiper-slide-next div");
    private final List<By> callings = List.of(By.cssSelector(".job-title [href*='/customer-success']"),
            By.cssSelector(".job-title [href*='/sales']"),
            By.cssSelector(".job-title [href*='/product-and']"),
            By.cssSelector(".job-title [href*='/finance']"),
            By.cssSelector(".job-title [href*='/quality']"),
            By.cssSelector(".job-title [href*='/product-design']"));

    public CareersPage(Driver driver) {
        super(driver);
    }

    public CareersPage checkCareersPageOpened() {
       Assert.assertTrue(driver.isElementVisible(mainTitle));
       return new CareersPage(driver);
    }

    public CareersPage checkCategoryTitlesAreVisible(List<PageTitle> titles) {
        for (PageTitle title : titles) {
            WebElement element = getElementWithTextInList(driver.findElements(categoryTitles), title.getTitle());
            driver.hoverToElement(element);
            Assert.assertTrue(element.isDisplayed());
        }

        return new CareersPage(driver);
    }

    public CareersPage checkCallingsAreVisible(int beforeButtonClickedCallingCount, boolean isButtonClicked) {
        if (!isButtonClicked)
            Assert.assertFalse(driver.isElementExist(callings.get(beforeButtonClickedCallingCount)));

        int lastIndexOfCallings = isButtonClicked ? callings.size() : beforeButtonClickedCallingCount;
        for (int i = 0; i < lastIndexOfCallings; i++)
            Assert.assertTrue(driver.isElementVisible(callings.get(i)));

        return new CareersPage(driver);
    }

    public CareersPage checkDefaultSizeTitleIsVisible(String titleName) {
        WebElement defaultSizeTitle = getElementWithTextInList(driver.findElements(defaultSizeTitles), titleName);
        driver.hoverToElement(defaultSizeTitle);
        Assert.assertTrue(defaultSizeTitle.isDisplayed());

        return new CareersPage(driver);
    }

    public CareersPage checkLocationCityIsVisible(String cityName) {
        driver.wait(5);
        WebElement locationCity = getElementWithTextInList(driver.findElements(locationCityNames), cityName);
        driver.hoverToElement(locationCity);
        Assert.assertTrue(locationCity.isDisplayed());

        return new CareersPage(driver);
    }

    public CareersPage checkLifeAtInsiderSliderImagesAreVisible() {
        WebElement sliderActiveImg = driver.findElement(sliderActiveImage);
        driver.hoverToElement(sliderActiveImg);
        Assert.assertTrue(sliderActiveImg.isDisplayed());
        Assert.assertTrue(driver.findElement(sliderNextImage).isDisplayed());

        return new CareersPage(driver);
    }

    public CareersPage clickSeeAllTeamsButton(int beforeButtonClickedCallingCount) {
        driver.findElement(seeAllTeamsButton).click();
        waitBeforeAllCallingsExist();
        checkCallingsAreVisible(beforeButtonClickedCallingCount, true);

        return new CareersPage(driver);
    }

    public CareersPage clickLocationRightArrowIcon() {
        Double progressBarBefore = getProgressBarCurrentProgress();
        driver.findElement(locationRightArrowIcon).click();
        Assert.assertNotEquals(getProgressBarCurrentProgress(), progressBarBefore, "Should change progress bar progress");

        return new CareersPage(driver);
    }

    public CareersPage clickLocationLeftArrowIcon() {
        driver.wait(10);
        Double progressBarBefore = getProgressBarCurrentProgress();
        driver.findElement(locationLeftArrowIcon).click();
        Assert.assertNotEquals(getProgressBarCurrentProgress(), progressBarBefore, "Should change progress bar progress");

        return new CareersPage(driver);
    }

    private void waitBeforeAllCallingsExist() {
        for (By calling : callings) driver.waitUntilElementExist(calling);
    }

    private Double getProgressBarCurrentProgress() {
        return Double.parseDouble(StringHelper.getNumericValueFromText(getElementAttribute(locationProgressBar, "style")));
    }

    public void waitUntilSliderNextImage(int maxWaitTime) {
        String currentImageStyle = getElementAttribute(sliderActiveImage, "style");

        boolean isNextSlide = false;
        for (int i = 0; i < maxWaitTime; i++) {
            if (!getElementAttribute(sliderActiveImage, "style").equals(currentImageStyle)) {
                isNextSlide = true;
                break;
            }
            driver.wait(2);
        }

        Assert.assertFalse(isNextSlide, "Slider cannot slide images");
    }
}
