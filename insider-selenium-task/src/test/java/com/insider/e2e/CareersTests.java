package com.insider.e2e;

import com.insider.base.BaseTest;
import com.insider.enums.Department;
import com.insider.enums.Location;
import com.insider.enums.PageTitle;
import com.insider.pages.HomePage;
import com.insider.pages.QualityAssurancePage;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class CareersTests extends BaseTest {

    @Test
    public void should_GetCareersPageAndComponentsFromHomePage() {
        final Map<String, String> dropdownOptions = Map.of("name", "Company",
                "subName", "Careers");
        final List<PageTitle> willBeControlledCategories = List.of(
                PageTitle.FIND_YOUR_CALLING,
                PageTitle.OUR_LOCATIONS
        );
        final int callingsVisibleBeforeButtonClicked = 3;

        new HomePage(driver)
                .checkHomePageOpened()
                .clickDropdownOnNavBar(dropdownOptions.get("name"))
                .clickDropdownSubOnSubs(dropdownOptions.get("subName"))
                .checkCareersPageOpened()
                .checkCategoryTitlesAreVisible(willBeControlledCategories)
                .checkCallingsAreVisible(callingsVisibleBeforeButtonClicked, false)
                .clickSeeAllTeamsButton(callingsVisibleBeforeButtonClicked)
                .checkLocationCityIsVisible(Location.BRAZIL.getCity())
                .clickLocationRightArrowIcon()
                .checkLocationCityIsVisible(Location.HOLLAND.getCity())
                .clickLocationLeftArrowIcon()
                .checkLocationCityIsVisible(Location.USA.getCity())
                .clickLocationLeftArrowIcon()
                .checkLocationCityIsVisible(Location.CHILE.getCity())
                .checkDefaultSizeTitleIsVisible(PageTitle.LIFE_AT_INSIDER.getTitle())
                .checkLifeAtInsiderSliderImagesAreVisible()
                .waitUntilSliderNextImage();
    }

    @Test
    public void should_GetQaJobsLeverPageFromQaCareersPageWithOpenPositionsPage() {
        final Location positionLocation = Location.TURKEY_ISTANBUL;
        final Department positionDepartment = Department.QUALITY_ASSURANCE;

        new QualityAssurancePage(driver)
                .getQualityAssurancePage(URL)
                .clickSeeAllQaJobsButton()
                .checkOpenPositionsPageOpened()
                .checkCurrentSelectedLocation(Location.ALL)
                .checkCurrentDepartment(positionDepartment)
                .clickDepartmentSelectBox(positionDepartment)
                .clickLocationSelectBox(positionLocation)
                .clickLocationSelectBoxOption(positionLocation)
                .checkCurrentSelectedLocation(positionLocation)
                .checkCurrentDepartment(positionDepartment)
                .checkAllPositionsDepartments(positionDepartment)
                .checkAllPositionsLocations(positionLocation)
                .hoverMouseToPositionItem(0)
                .clickViewRoleButton(0)
                .checkJobsLeverPageOpened(positionDepartment.getName(), positionLocation.getName());
    }
}
