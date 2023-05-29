package com.example.ui.metamask;

import com.codeborne.selenide.*;
import com.example.ui.configuration.AppConfiguration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.*;

import static com.codeborne.selenide.Selenide.*;

public class MetamaskFlow {

    private final AppConfiguration appConfiguration = ConfigFactory.create(AppConfiguration.class, Map.of());
    private final static String PASSWORD = System.getenv("PASSWORD");
    private final static List<String> SECRETS = Arrays.asList(System.getenv("FIRST_S"),
                                                              System.getenv("SECOND_S"),
                                                              System.getenv("THIRD_S"),
                                                              System.getenv("FOURTH_S"),
                                                              System.getenv("FIFTH_S"),
                                                              System.getenv("SIXTH_S"),
                                                              System.getenv("SEVENTH_S"),
                                                              System.getenv("EIGHT_S"),
                                                              System.getenv("NINTH_S"),
                                                              System.getenv("TENTH_S"),
                                                              System.getenv("ELEVENTH_S"),
                                                              System.getenv("TWELFTH_S"));

    public void switchToMetamaskExtensionTab() {
        switchTo().window(appConfiguration.extensionName());
    }

    public void login() {
        $(By.id("onboarding__terms-checkbox")).click();
        $x("//button[contains(@data-testid, 'onboarding-import-wallet')]").click();
        $x("//button[contains(@data-testid, 'metametrics-no-thanks')]").click();
        for (int i = 0; i < SECRETS.size(); ++i) {
            $(By.id("import-srp__srp-word-" + i)).sendKeys(SECRETS.get(i));
        }
        $x("//button[contains(@data-testid, 'import-srp-confirm')]").click();
        $x("//input[contains(@data-testid, 'create-password-new')]").sendKeys(PASSWORD);
        $x("//input[contains(@data-testid, 'create-password-confirm')]").sendKeys(PASSWORD);
        $x("//input[contains(@data-testid, 'create-password-terms')]").click();
        $x("//button[contains(@data-testid, 'create-password-import')]").click();
        $x("//button[contains(@data-testid, 'onboarding-complete-done')]")
                .shouldBe(Condition.interactable)
                .click();
        $x("//button[contains(@data-testid, 'pin-extension-next')]").click();
        $x("//button[contains(@data-testid, 'pin-extension-done')]").click();
        //$x("//button[@class='button btn--rounded btn-primary']")
        //        .shouldBe(Condition.visible)
        //        .click();
        //$x("//button[contains(@data-testid, 'page-container-footer-next')]").click();
        $x("//button[contains(@data-testid, 'popover-close')]").click();
    }

    public void addNewNetwork(NetworkConnectionData data) {
        Selenide.open(appConfiguration.extensionAddNetworkAddress());
        ElementsCollection inputs = $$x("//input[@class='form-field__input']");
        inputs.get(0).sendKeys(data.getNetworkName());
        inputs.get(1).sendKeys(data.getRpcUrl());
        inputs.get(2).sendKeys(data.getChainId());
        inputs.get(3).sendKeys(data.getCurrencySymbol());
        $x("//button[@class='button btn--rounded btn-primary']").shouldBe(Condition.interactable)
                .click();
    }

    public void approveTransaction() {
        Selenide.switchTo()
                .window(appConfiguration.extensionNotification(),
                        Duration.ofMillis(Configuration.pageLoadTimeout));
        $x("//button[contains(@data-testid, 'page-container-footer-next')]")
                .shouldBe(Condition.interactable)
                .click();
        Selenide.switchTo().window(appConfiguration.appName());
    }
}
