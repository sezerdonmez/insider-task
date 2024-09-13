package com.insider.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insider.enums.Status;
import com.insider.modals.request.PetRequest;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.insider.utils.Config.getProperty;

public class PetController extends BaseController {

    private final String PATH = "/pet";

    public PetController() {
        super(getProperty("baseUrl"));
    }

    public Response getPetWithId(Long petId) {
        return getRequestResponse(String.format("%s/%s", PATH, petId));
    }

    public Response createPet(Map<String, String> petMap, Status petStatus) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return postRequestWithBodyString(PATH, objectMapper.writeValueAsString(getPetRequest(petMap, petStatus)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Response updatePet(Map<String, String> petMap, Status petStatus) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return putRequestWithBodyString(PATH, objectMapper.writeValueAsString(getPetRequest(petMap, petStatus)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Response getPetWithStatus(String statusName) {
        Map<String, String> queryParams = Map.of("status", statusName);

        return getRequestWithQueryParams(String.format("%s/findByStatus", PATH), queryParams);
    }

    public Response deletePetWithId(Long petId) {
        return deleteRequestResponse(String.format("%s/%s", PATH, petId));
    }

    private PetRequest getPetRequest(Map<String, String> petMap, Status petStatus) {
        return PetRequest.builder()
                .id(Integer.parseInt(petMap.get("id")))
                .category(PetRequest.Category.builder()
                        .id(Integer.parseInt(petMap.get("categoryId")))
                        .name(petMap.get("categoryName"))
                        .build())
                .name(petMap.get("name"))
                .photoUrls(new ArrayList<>())
                .tags(List.of(PetRequest.Tag.builder()
                                .id(1)
                                .name("new-task")
                                .build()))
                .status(petStatus.getName())
                .build();
    }
}
