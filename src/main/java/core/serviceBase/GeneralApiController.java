package core.serviceBase;


import core.testBase.Keywords;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static core.utils.LogUtils.logInfo;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class GeneralApiController {
    private RequestSpecification spec;

    public GeneralApiController() {
    }

    public GeneralApiController(String baseUrl) {
        this.spec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setBasePath("/")
                .addHeader(Keywords.CONTENT_TYPE, Keywords.APPLICATION_JSON)
                .build();
    }

    protected ReadableResponse getRequest(String endPoint) {
        Response response = given()
                .spec(spec)
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
                .when()
                .delete(endPoint)
                .then()
                .extract()
                .response();

        logInfo(format("Endpoint: %s, Request Type: %s", endPoint, "delete"));

        return new ReadableResponse(response);
    }

}
