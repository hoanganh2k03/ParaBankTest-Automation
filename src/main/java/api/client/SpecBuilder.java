package api.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.ConfigReader;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("url_api"))
                .setContentType(ContentType.JSON)  // Chuyển sang JSON
                .setAccept(ContentType.JSON)       // Chuyển sang JSON
                .setConfig(RestAssuredConfig.config()
                        .objectMapperConfig(new ObjectMapperConfig()
                                .jackson2ObjectMapperFactory((cls, charset) -> new ObjectMapper())))
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)  // Chuyển sang JSON
                .log(LogDetail.ALL)
                .build();
    }
}
