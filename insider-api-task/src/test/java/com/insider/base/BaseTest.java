package com.insider.base;

import com.insider.controllers.PetController;
import com.insider.enums.Category;
import com.insider.enums.Status;
import com.insider.utils.LogUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import java.util.Map;

import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;

public class BaseTest extends BaseTestHelper {

    protected static PetController petController = new PetController();

    protected static final Map<String, String> createdPetMap = Map.of("id", "99",
            "categoryId", Category.CAT.getId().toString(),
            "categoryName", Category.CAT.getName());
    protected static final Map<String, String> willUpdatedPetMap = Map.of("id", "88",
            "categoryId", Category.BIRD.getId().toString(),
            "categoryName", Category.BIRD.getName());
    protected static final Map<String, String> willDeletedPetMap = Map.of("id", "77",
            "categoryId", Category.DOG.getId().toString(),
            "categoryName", Category.DOG.getName());
    protected static final Status createdPetStatus = Status.SOLD;
    protected static final Long deletedPetId = 1000000L;

    @BeforeSuite
    public void beforeTest() {
        // Create Pets
        LogUtils.logInfo(format("Test Datas will be inserted before tests ids: %s %s %s",
                createdPetMap.get("id"), willUpdatedPetMap.get("id"), willDeletedPetMap.get("id")));

        Assert.assertEquals(petController.createPet(createdPetMap, createdPetStatus).statusCode(), SC_OK);
        Assert.assertEquals(petController.createPet(willUpdatedPetMap, createdPetStatus).statusCode(), SC_OK);
        Assert.assertEquals(petController.createPet(willDeletedPetMap, createdPetStatus).statusCode(), SC_OK);

        LogUtils.logInfo("Test Datas inserted successfully");

        // Delete Pet If Exist
        LogUtils.logInfo(format("Test Datas will be deleted before tests ids: %d", deletedPetId));
        petController.deletePetWithId(deletedPetId);

        LogUtils.logInfo("Test Datas deleted successfully");
    }
}
