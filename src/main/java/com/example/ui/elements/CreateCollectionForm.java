package com.example.ui.elements;

import com.codeborne.selenide.*;
import com.example.ui.data.CreateCollectionFormDto;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class CreateCollectionForm {

    private final SelenideElement collectionNameInput;
    private final SelenideElement collectionSymbolInput;
    private final SelenideElement collectionTokenUriInput;
    private final SelenideElement createButton;

    public CreateCollectionForm() {
        SelenideElement container = $x("//b[contains(text(), 'Deploy Collection')]/following-sibling::form");
        collectionNameInput = container.$x(".//input[@placeholder = 'Enter collection name']");
        collectionSymbolInput = container.$x(".//input[@placeholder = 'Enter collection symbol']");
        collectionTokenUriInput = container.$x(".//input[@placeholder = 'Enter collection token URI']");
        createButton = container.$x(".//button[@type = 'submit']");
    }

    public CreateCollectionForm fillFormWithFormData(CreateCollectionFormDto data) {
        collectionNameInput.shouldBe(Condition.visible, Duration.ofMillis(Configuration.timeout))
                .setValue(data.getCollectionName());
        collectionSymbolInput.setValue(data.getCollectionSymbol());
        collectionTokenUriInput.setValue(data.getCollectionTokenUri());
        return this;
    }

    public CreateCollectionForm createCollection() {
        createButton.click();
        return this;
    }
}
