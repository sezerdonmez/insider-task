package com.insider.enums;

import com.insider.pages.CareersPage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PageTitle {


    OUR_STORY(CareersPage.class.getName(), "Our story"),
    FIND_YOUR_CALLING(CareersPage.class.getName(), "Find your calling"),
    OUR_LOCATIONS(CareersPage.class.getName(), "Our Locations"),
    LIFE_AT_INSIDER(CareersPage.class.getName(), "Life at Insider"),
    WHY_ONE_OF_US(CareersPage.class.getName(), "Why Become One of Us");

    private final String page;
    private final String title;
}
