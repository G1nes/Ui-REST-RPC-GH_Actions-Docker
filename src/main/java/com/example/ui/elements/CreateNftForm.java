package com.example.ui.elements;

import com.codeborne.selenide.SelenideElement;
import com.example.ui.data.CreateNftFormDto;

import static com.codeborne.selenide.Selenide.$x;

public class CreateNftForm {

    private final SelenideElement collectionAddressInput;
    private final SelenideElement recipientAddressInput;
    private final SelenideElement tokenIdInput;
    private final SelenideElement mintButton;

    public CreateNftForm() {
        SelenideElement container = $x("//b[contains(text(), 'Mint NFT')]/following-sibling::form");
        collectionAddressInput = container.$x(".//input[@placeholder = 'Enter collection address']");
        recipientAddressInput = container.$x(".//input[@placeholder = 'Enter recipient address']");
        tokenIdInput = container.$x(".//input[@placeholder = 'Enter token id']");
        mintButton = container.$x(".//button[@type = 'submit']");
    }

    public CreateNftForm fillFormWithFormData(CreateNftFormDto data) {
        collectionAddressInput.setValue(data.getCollectionAddress());
        recipientAddressInput.setValue(data.getRecipientAddress());
        tokenIdInput.setValue(String.valueOf(data.getTokenId()));
        return this;
    }

    public CreateNftForm createNft() {
        mintButton.click();
        return this;
    }
}
