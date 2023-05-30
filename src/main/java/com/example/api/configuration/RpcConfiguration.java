package com.example.api.configuration;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:configuration.properties")
public interface RpcConfiguration extends Config {

    @Config.Key("rpc.url")
    String rpcUrl();
}
