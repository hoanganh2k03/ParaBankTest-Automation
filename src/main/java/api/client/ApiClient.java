package api.client;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiClient {

    public static <T> T get(String endpoint, Class<T> responseClass) {
        return given(SpecBuilder.getRequestSpec())
                .when()
                .get(endpoint)
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .statusCode(200)
                .extract()
                .as(responseClass);
    }

    public static <T> T post(String endpoint, Object body, Class<T> responseClass) {
        return given(SpecBuilder.getRequestSpec())
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .statusCode(200)
                .extract()
                .as(responseClass);
    }
}
