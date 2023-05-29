package com.example.api.configuration;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:configuration.properties")
public interface ApiTestsConfiguration extends Config {

    @Config.Key("backend.url")
    String backendUrl();

    @Config.Key("backend.polling_timeout")
    Long backendPollingTimeout();

    @Config.Key("backend.polling_delay")
    Long backendPollingDelay();
}
