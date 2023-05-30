package com.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.example.api.configuration.ApiTestsConfiguration;
import com.example.api.configuration.RestAssuredConfiguration;
import com.example.ui.configuration.*;
import com.example.ui.metamask.MetamaskFlow;
import com.example.ui.metamask.NetworkConnectionData;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;

import java.util.Map;

@Slf4j
public abstract class BaseTest {

    protected final TestDataGenerator generator = new TestDataGenerator();
    private static final UiTestsConfiguration SELENIDE_CONFIGURATION = ConfigFactory.create(UiTestsConfiguration.class, Map.of());
    private static final ApiTestsConfiguration API_TESTS_CONFIGURATION = ConfigFactory.create(ApiTestsConfiguration.class, Map.of());
    private static final AppConfiguration APP_CONFIGURATION = ConfigFactory.create(AppConfiguration.class, Map.of());
    private static final BlockchainNetworkConfiguration BLOCKCHAIN_NETWORK_CONFIGURATION = ConfigFactory.create(BlockchainNetworkConfiguration.class, Map.of());
    private static final NetworkConnectionData networkData;
    private final MetamaskFlow metamaskFlow = new MetamaskFlow();

    static {
        networkData = NetworkConnectionData.builder()
                .networkName(BLOCKCHAIN_NETWORK_CONFIGURATION.networkName())
                .rpcUrl(BLOCKCHAIN_NETWORK_CONFIGURATION.rpcUrl())
                .chainId(BLOCKCHAIN_NETWORK_CONFIGURATION.chainId())
                .currencySymbol(BLOCKCHAIN_NETWORK_CONFIGURATION.currencySymbol())
                .build();
    }


    @BeforeAll
    public static void init() {
        new SelenideConfiguration(SELENIDE_CONFIGURATION).configure();
        new RestAssuredConfiguration(API_TESTS_CONFIGURATION).configure();
    }

    @BeforeEach
    public void setUpApplication() {
        Selenide.open(Configuration.baseUrl);
        metamaskFlow.switchToMetamaskExtensionTab();
        metamaskFlow.restoreWalletAccess();
        metamaskFlow.addNewNetwork(networkData);

        Selenide.switchTo().window(APP_CONFIGURATION.appName());
        Selenide.refresh();
    }

    @AfterEach
    public void closeBrowser() {
        Selenide.closeWindow();
        Selenide.closeWebDriver();
    }
}
