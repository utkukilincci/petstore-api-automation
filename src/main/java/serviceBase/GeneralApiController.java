package serviceBase;


import base.Keywords;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import static helpers.LogUtils.logInfo;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class GeneralApiController {
    private RequestSpecification spec;

    public GeneralApiController() {
    }

    public GeneralApiController(String baseUrl) {
        this.spec = new RequestSpecBuilder().setBaseUri(baseUrl).setBasePath("/").build();
    }

    protected ReadableResponse getRequest(String endPoint) {
        Response response = given()
                .spec(spec)
                .header(Keywords.CONTENT_TYPE, Keywords.APPLICATION_JSON)
                .when()
                .get(endPoint)
                .then()
                .extract()
                .response();

        logInfo(format("Endpoint: %s, Request Type: %s", endPoint, "get"));

        return new ReadableResponse(response);
    }

    protected ReadableResponse postRequest(JSONObject jsonObject, String endPoint) {
        Response response = given()
                .spec(spec)
                .header(Keywords.CONTENT_TYPE, Keywords.APPLICATION_JSON)
                .body(jsonObject.toString())
                .when()
                .post(endPoint)
                .then()
                .extract()
                .response();

        logInfo(format("Endpoint: %s, Request Type: %s, Body: %s ", endPoint, "post", jsonObject.toString()));

        return new ReadableResponse(response);
    }

    protected ReadableResponse putRequest(JSONObject jsonObject, String endPoint) {
        Response response = given()
                .spec(spec)
                .header(Keywords.CONTENT_TYPE, Keywords.APPLICATION_JSON)
                .body(jsonObject.toString())
                .when()
                .put(endPoint)
                .then()
                .extract()
                .response();

        logInfo(format("Endpoint: %s, Request Type: %s, Body: %s ", endPoint, "put", jsonObject.toString()));

        return new ReadableResponse(response);
    }

    protected ReadableResponse deleteRequest(String endPoint) {
        Response response = given()
                .spec(spec)
                .header(Keywords.CONTENT_TYPE, Keywords.APPLICATION_JSON)
                .when()
                .delete(endPoint)
                .then()
                .extract()
                .response();

        logInfo(format("Endpoint: %s, Request Type: %s", endPoint, "delete"));

        return new ReadableResponse(response);
    }

}
