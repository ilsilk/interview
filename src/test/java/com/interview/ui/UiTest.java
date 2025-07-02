package com.interview.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.interview.BaseTest;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Map;

import static com.interview.common.utils.PropertiesUtils.getStringProperty;

public abstract class UiTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUpUi() {
        Configuration.browser = getStringProperty("browser");
        Configuration.browserSize = getStringProperty("browser.size");
        Configuration.browserCapabilities.setCapability("selenoid:options", Map.of("enableVNC", true));
        Configuration.baseUrl = getStringProperty("ui.base.url");
        Configuration.remote = getStringProperty("remote");
        Configuration.downloadsFolder = "target/downloads";
        Configuration.reportsFolder = "target/reports/tests";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
