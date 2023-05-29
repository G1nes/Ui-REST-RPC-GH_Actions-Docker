package com.example.ui.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.example.ui.data.*;
import com.example.ui.elements.*;
import org.awaitility.Awaitility;

import java.time.Duration;
import java.util.List;

public class NftGenerationPage {

    private final CreateCollectionForm createCollectionForm;
    private final CreateNftForm createNftForm;
    private final EventsDisplay eventsDisplay;

    public NftGenerationPage() {
        createCollectionForm = new CreateCollectionForm();
        createNftForm = new CreateNftForm();
        eventsDisplay = new EventsDisplay();
    }

    public void createCollectionWithData(CreateCollectionFormDto data) {
        createCollectionForm.fillFormWithFormData(data);
        int tabsCountBeforeTransaction = Selenide.webdriver().object().getWindowHandles().size();
        createCollectionForm.createCollection();
        waitForNewTab(tabsCountBeforeTransaction);
    }

    public void createNftWithData(CreateNftFormDto data) {
        createNftForm.fillFormWithFormData(data);
        int tabsCountBeforeTransaction = Selenide.webdriver().object().getWindowHandles().size();
        createNftForm.createNft();
        waitForNewTab(tabsCountBeforeTransaction);
    }

    public List<EventDto> collectEvents() {
        return eventsDisplay.collectEvents();
    }

    private void waitForNewTab(int initialTabCount) {
        Awaitility.await()
                .pollInSameThread()
                .atMost(Duration.ofMillis(Configuration.pageLoadTimeout))
                .until(() -> initialTabCount < Selenide.webdriver().object().getWindowHandles().size());
    }
}
