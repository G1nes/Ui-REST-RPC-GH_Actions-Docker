package com.example.ui.configuration;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.logging.Level;

public class SelenideConfiguration {

    private final UiTestsConfiguration configuration;

    public SelenideConfiguration(UiTestsConfiguration uiTestsConfiguration) {
        configuration = uiTestsConfiguration;
    }

    public void configure() {
        LoggingPreferences loggingPreferences = new LoggingPreferences();
        loggingPreferences.enable(LogType.BROWSER, Level.INFO);
        loggingPreferences.enable(LogType.DRIVER, Level.INFO);
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("loggingPrefs", loggingPreferences);
        ChromeOptions chromeOptions = new ChromeOptions()
                .addExtensions(new File("src/main/resources/10.30.4_0.crx"))
                .addArguments("--no-sandbox")
                .addArguments("--disable-dev-shm-usage")
                .addArguments("--headless");
        chromeOptions.setCapability(ChromeOptions.CAPABILITY, desiredCapabilities);

        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        Configuration.baseUrl = configuration.browserUrl();
        Configuration.browserSize = configuration.browserResolution();
        Configuration.timeout = Long.parseLong(configuration.browserElementTimeoutMillis());
        Configuration.pageLoadTimeout = Long.parseLong(configuration.browserPageTimeoutMillis());
    }
}
