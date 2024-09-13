package com.insider.helpers;

import com.insider.enums.Category;
import com.insider.enums.Status;
import org.testng.annotations.DataProvider;

public class DataProviderHelper {

    @DataProvider(name = "pet-categories-statuses")
        public static Object[][] petCategoriesStatuses() {
            return new Object[][]{
                    {Category.CAT, Status.PENDING},
                    {Category.DOG, Status.AVAILABLE},
                    {Category.FROG, Status.SOLD}
            };
        }

    @DataProvider(name = "pet-statuses")
    public static Object[][] petStatuses() {
        return new Object[][]{
                {Status.PENDING},
                {Status.AVAILABLE},
                {Status.SOLD}
        };
    }
}
