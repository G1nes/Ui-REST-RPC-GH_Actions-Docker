package com.example.ui.configuration;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:configuration.properties")
public interface UiTestsConfiguration extends Config {

    @Config.Key("browser.url")
    String browserUrl();

    @Config.Key("browser.resolution")
    String browserResolution();

    @Config.Key("browser.element.millis_timeout")
    String browserElementTimeoutMillis();

    @Config.Key("browser.page.millis_timeout")
    String browserPageTimeoutMillis();
}
