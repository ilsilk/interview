package com.interview.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.interview.BaseTest;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.Map;

import static com.interview.common.utils.PropertiesUtils.getStringProperty;

public abstract class UiTest extends BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        Configuration.browser = getStringProperty("browser");
        Configuration.browserSize = getStringProperty("browser.size");
        Configuration.browserCapabilities.setCapability("selenoid:options", Map.of("enableVNC", true));
        Configuration.baseUrl = getStringProperty("ui.base.url");
        Configuration.remote = getStringProperty("remote");
        Configuration.downloadsFolder = "target/downloads";
        Configuration.reportsFolder = "target/reports/tests";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
