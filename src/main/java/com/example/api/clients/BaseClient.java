package com.example.api.clients;

import com.example.api.configuration.ApiTestsConfiguration;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.awaitility.Awaitility;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public abstract class BaseClient {

    private static final ApiTestsConfiguration API_TESTS_CONFIGURATION = ConfigFactory.create(ApiTestsConfiguration.class, Map.of());

    protected void pollUntil(Callable<Boolean> callable) {
        Awaitility.await()
                .pollInSameThread()
                .atMost(API_TESTS_CONFIGURATION.backendPollingTimeout(), TimeUnit.SECONDS)
                .pollDelay(API_TESTS_CONFIGURATION.backendPollingDelay(), TimeUnit.SECONDS)
                .until(callable);
    }

    protected <T> T get(String endpoint, Class<T> responseClass) {
        return createDefaultSpec()
                .get(endpoint)
                .as(responseClass);
    }

    private RequestSpecification createDefaultSpec() {
        return given().headers(Map.of("Accept", "*/*"));
    }

}
