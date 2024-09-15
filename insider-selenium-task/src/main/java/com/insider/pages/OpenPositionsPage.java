package com.insider.pages;

import com.insider.driver.Driver;
import com.insider.enums.Department;
import com.insider.enums.Location;
import org.openqa.selenium.By;
import org.testng.Assert;

public class OpenPositionsPage extends BasePage {
    private final String PATH = "/careers/open-positions/?department=qualityassurance";
    private final By locationSelectBox = By.cssSelector("span [id*='filter-by-location']");
    private final By departmentSelectBox = By.cssSelector("span [id*='filter-by-department']");
    private final By positionDepartments = By.cssSelector(".position-department");
    private final By positionLocations = By.cssSelector(".position-location");
    private final By positionItems = By.cssSelector(".position-list-item");
    private final By viewRoleButton = By.cssSelector("[href*='/jobs.lever.co/useinsider/']");

    public OpenPositionsPage(Driver driver) {
        super(driver);
    }

    public OpenPositionsPage checkOpenPositionsPageOpened() {
        Assert.assertTrue(driver.findElement(locationSelectBox).isDisplayed());
        return new OpenPositionsPage(driver);
    }

    public OpenPositionsPage checkCurrentSelectedLocation(Location location) {
        By elementLocator = By.cssSelector(String.format("[title='%s']", location.getName()));
        Assert.assertTrue(driver.findElement(elementLocator).isDisplayed());

        return new OpenPositionsPage(driver);
    }

    public OpenPositionsPage checkCurrentDepartment(Department department) {
        waitForPositionDepartmentsDesired(department);

        By elementLocator = By.cssSelector(String.format("[title='%s']", department.getName()));
        Assert.assertTrue(driver.findElement(elementLocator).isDisplayed());

        return new OpenPositionsPage(driver);
    }

    public OpenPositionsPage waitForPositionDepartmentsDesired(Department department) {
        driver.waitUntilAllElementsTextDesired(positionDepartments, department.getName());
        return new OpenPositionsPage(driver);
    }

    public OpenPositionsPage clickLocationSelectBox(Location location) {
        clickOnSelectBox(locationSelectBox, location.getName());

        return new OpenPositionsPage(driver);
    }

    public OpenPositionsPage clickDepartmentSelectBox(Department department) {
        clickOnSelectBox(departmentSelectBox, department.getName());

        return new OpenPositionsPage(driver);
    }

    public OpenPositionsPage clickLocationSelectBoxOption(Location location) {
        clickSelectBoxOption(location.getName());

        return new OpenPositionsPage(driver);
    }

    public OpenPositionsPage checkAllPositionsDepartments(Department department) {
        waitForPositionDepartmentsDesired(department);

        checkAllElementsTextsEquals(positionDepartments, department.getName());

        return new OpenPositionsPage(driver);
    }

    public OpenPositionsPage checkAllPositionsLocations(Location location) {
        checkAllElementsTextsEquals(positionLocations, location.getName());

        return new OpenPositionsPage(driver);
    }

    public OpenPositionsPage hoverMouseToPositionItem(int itemIndex) {
        driver.hoverElementWithPosition(driver.findElements(positionItems).get(itemIndex), 1, 1);
        driver.waitUntilElementVisible(viewRoleButton);

        return new OpenPositionsPage(driver);
    }

    public JobsLeverPage clickViewRoleButton(int itemIndex) {
        driver.findElements(viewRoleButton).get(itemIndex).click();
        driver.changeCurrentWindow();

        return new JobsLeverPage(driver);
    }
}
