package com.example.api.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;

public class RestAssuredConfiguration {

    private final ApiTestsConfiguration configuration;

    public RestAssuredConfiguration(ApiTestsConfiguration apiTestsConfiguration) {
        configuration = apiTestsConfiguration;
    }

    public void configure() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(getMapperConfig());
        RestAssured.baseURI = configuration.backendUrl();
    }

    private static ObjectMapperConfig getMapperConfig() {
        return new ObjectMapperConfig().jackson2ObjectMapperFactory((cls, charset) -> new ObjectMapper()
                .findAndRegisterModules()
                .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true));
    }
}
