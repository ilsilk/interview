package com.interview.api;

import com.github.viclovsky.swagger.coverage.FileSystemOutputWriter;
import com.github.viclovsky.swagger.coverage.SwaggerCoverageRestAssured;
import com.interview.BaseTest;
import io.qameta.allure.awaitility.AllureAwaitilityListener;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.awaitility.Awaitility;
import org.testng.annotations.BeforeSuite;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

import static com.github.viclovsky.swagger.coverage.SwaggerCoverageConstants.OUTPUT_DIRECTORY;
import static com.interview.common.utils.PropertiesUtils.getStringProperty;

public abstract class ApiTest extends BaseTest {

    protected static RequestSpecBuilder defaultRequestBuilder() {
        return new RequestSpecBuilder().setContentType(ContentType.JSON).setAccept(ContentType.JSON).addFilters(
                        List.of(new RequestLoggingFilter(), new ResponseLoggingFilter(), new AllureRestAssured(),
                                new SwaggerCoverageRestAssured(
                                        new FileSystemOutputWriter(Paths.get("target/" + OUTPUT_DIRECTORY)))))
                .setBaseUri(getStringProperty("api.base.url"));
    }

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        Awaitility.setDefaultConditionEvaluationListener(new AllureAwaitilityListener());
        Awaitility.setDefaultPollInterval(Duration.ofSeconds(10));
        Awaitility.setDefaultTimeout(Duration.ofMinutes(5));
        Awaitility.pollInSameThread();
    }
}
