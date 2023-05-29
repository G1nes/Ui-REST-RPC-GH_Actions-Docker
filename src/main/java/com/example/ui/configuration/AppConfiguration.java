package com.example.ui.configuration;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:configuration.properties")
public interface AppConfiguration extends Config {
    @Config.Key("extension.name")
    String extensionName();

    @Config.Key("extension.add_network.address")
    String extensionAddNetworkAddress();

    @Config.Key("extension.notification")
    String extensionNotification();

    @Config.Key("app.name")
    String appName();
}
