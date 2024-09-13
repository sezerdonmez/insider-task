package com.insider;

import com.insider.base.BaseTest;
import com.insider.enums.Category;
import com.insider.enums.Status;
import com.insider.helpers.DataProviderHelper;
import com.insider.helpers.RandomizeHelper;
import com.insider.modals.response.ErrorResponse;
import com.insider.modals.response.PetResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;

@Feature("PetTests")
public class PetTests extends BaseTest {


    @Description("shouldCreateNewPetToPetStoreWithDifferentStatuses")
    @Test(dataProvider = "pet-categories-statuses", dataProviderClass = DataProviderHelper.class)
    public void shouldCreateNewPetToPetStoreWithDifferentStatuses(Category category, Status status) {
        final Map<String, String> petMap = Map.of("id", RandomizeHelper.getRandomNumberInRange(100, 999999).toString(),
                "categoryId", category.getId().toString(),
                "categoryName", category.getName());

        Response response = petController.createPet(petMap, status);
        Assert.assertEquals(response.statusCode(), SC_OK, "Response code should be " + SC_OK);

        Response getPetResponse = petController.getPetWithId(Long.parseLong(petMap.get("id")));
        Assert.assertEquals(response.statusCode(), SC_OK, "Response code should be " + SC_OK);

        PetResponse pet = getPetResponse.as(PetResponse.class);
        Assert.assertEquals(pet.getId().toString(), petMap.get("id"));
        Assert.assertEquals(pet.getCategory().getId().toString(), petMap.get("categoryId"));
        Assert.assertEquals(pet.getStatus(), status.getName());
    }

    @Description("shouldUpdatePetFromPetStoreWithDifferentStatuses")
    @Test(dataProvider = "pet-categories-statuses", dataProviderClass = DataProviderHelper.class)
    public void shouldUpdatePetFromPetStoreWithDifferentStatuses(Category category, Status status) {
        final Map<String, String> petMap = Map.of("id", willUpdatedPetMap.get("id"),
                "categoryId", category.getId().toString(),
                "categoryName", category.getName());

        Response response = petController.updatePet(petMap, status);
        Assert.assertEquals(response.statusCode(), SC_OK, "Response code should be " + SC_OK);

        Response getPetResponse = petController.getPetWithId(Long.parseLong(petMap.get("id")));
        Assert.assertEquals(response.statusCode(), SC_OK, "Response code should be " + SC_OK);

        PetResponse pet = getPetResponse.as(PetResponse.class);
        Assert.assertEquals(pet.getId().toString(), petMap.get("id"));
        Assert.assertEquals(pet.getCategory().getId().toString(), petMap.get("categoryId"));
        Assert.assertEquals(pet.getStatus(), status.getName());
    }

    @Description("shouldGetCreatedPetWithId")
    @Test()
    public void shouldGetCreatedPetWithId() {
        Response response = petController.getPetWithId(Long.parseLong(createdPetMap.get("id")));
        Assert.assertEquals(response.statusCode(), SC_OK, "Response code should be " + SC_OK);

        PetResponse pet = response.as(PetResponse.class);
        Assert.assertEquals(pet.getId().toString(), createdPetMap.get("id"));
        Assert.assertEquals(pet.getCategory().getId().toString(), createdPetMap.get("categoryId"));
        Assert.assertEquals(pet.getStatus(), createdPetStatus.getName());
    }

    @Description("shouldNotGetNotCreatedPetWithId")
    @Test()
    public void shouldNotGetNotCreatedPetWithId() {
        Response response = petController.getPetWithId(deletedPetId);
        Assert.assertEquals(response.statusCode(), SC_NOT_FOUND, "Response code should be " + SC_NOT_FOUND);

        ErrorResponse errorResponse = response.as(ErrorResponse.class);
        Assert.assertEquals(errorResponse.getCode(), 1);
        Assert.assertEquals(errorResponse.getType(), "error");
        Assert.assertEquals(errorResponse.getMessage(), "Pet not found");
    }

    @Description("shouldDeleteCreatedPetWithId")
    @Test()
    public void shouldDeleteCreatedPetWithId() {
        Response response = petController.deletePetWithId(Long.parseLong(willDeletedPetMap.get("id")));
        Assert.assertEquals(response.statusCode(), SC_OK, "Response code should be " + SC_OK);
    }

    @Description("shouldNotDeleteNotCreatedPetWithId")
    @Test()
    public void shouldNotDeleteNotCreatedPetWithId() {
        Response response = petController.deletePetWithId(deletedPetId);
        Assert.assertEquals(response.statusCode(), SC_NOT_FOUND, "Response code should be " + SC_NOT_FOUND);
    }

    @Description("shouldGetCreatedPetsWithStatus")
    @Test(dataProvider = "pet-statuses", dataProviderClass = DataProviderHelper.class)
    public void shouldGetCreatedPetsWithStatus(Status status) {
        Response response = petController.getPetWithStatus(status.getName());
        Assert.assertEquals(response.statusCode(), SC_OK, "Response code should be " + SC_OK);

        List<PetResponse> pets = response.body().jsonPath().getList(".", PetResponse.class);
        for (PetResponse pet : pets) Assert.assertEquals(pet.getStatus(), status.getName());
    }

    @Description("shouldNotGetPetsWithWrongStatus")
    @Test()
    public void shouldNotGetPetsWithWrongStatus() {
        Response response = petController.getPetWithStatus("wrong");
        Assert.assertEquals(response.statusCode(), SC_OK, "Response code should be " + SC_OK);

        List<PetResponse> pets = response.body().jsonPath().getList(".", PetResponse.class);
        Assert.assertTrue(pets.isEmpty());
    }
}
