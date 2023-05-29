package com.example.ui.configuration;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:configuration.properties")
public interface BlockchainNetworkConfiguration extends Config {
    @Config.Key("network.name")
    String networkName();

    @Config.Key("network.rpc.url")
    String rpcUrl();

    @Config.Key("network.chain.id")
    String chainId();

    @Config.Key("network.currency")
    String currencySymbol();
}
