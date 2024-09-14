package com.insider.clients;

import com.insider.utils.LogUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

public class BaseClient {

    private final RequestSpecification spec;

    public BaseClient(String baseUrl) {
        this.spec = new RequestSpecBuilder().setBaseUri(baseUrl).setBasePath("/").build();
    }

    protected Response getRequestResponse(String endpoint) {
        RequestSpecification specification = given()
                .spec(spec);

        Response response = specification
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();

        LogUtils.logInfo(format("Endpoint: %s, Request Type: %s", endpoint, "get"));
        return response;
    }

    protected Response getRequestWithQueryParams(String endpoint, Map<String, String> queryParams) {
        RequestSpecification specification = given()
                .spec(spec);

        for (Map.Entry<String, String> entry : queryParams.entrySet())
            specification.queryParam(entry.getKey(), entry.getValue());

        Response response = specification
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();

        LogUtils.logInfo(format("Endpoint: %s, Request Type: %s", endpoint, "get"));
        return response;
    }

    protected Response postRequestWithBodyString(String endpoint, String bodyString) {
        RequestSpecification specification = given()
                .spec(spec);

        Response response = specification
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .body(bodyString)
                .with()
                .contentType(APPLICATION_JSON.getMimeType())
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();

        LogUtils.logInfo(format("Endpoint: %s, Request Type: %s, Body: %s", endpoint, "post", bodyString));
        return response;
    }

    protected Response putRequestWithBodyString(String endpoint, String bodyString) {
        RequestSpecification specification = given()
                .spec(spec);

        Response response = specification
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .body(bodyString)
                .with()
                .contentType(APPLICATION_JSON.getMimeType())
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();

        LogUtils.logInfo(format("Endpoint: %s, Request Type: %s, Body: %s", endpoint, "put", bodyString));
        return response;
    }

    protected Response deleteRequestResponse(String endpoint) {
        RequestSpecification specification = given()
                .spec(spec);

        Response response = specification
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();

        LogUtils.logInfo(format("Endpoint: %s, Request Type: %s", endpoint, "delete"));
        return response;
    }
}
