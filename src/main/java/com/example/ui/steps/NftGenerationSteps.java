package com.example.ui.steps;

import com.example.ui.data.*;
import com.example.ui.mapper.EventMapper;
import com.example.ui.metamask.MetamaskFlow;
import com.example.ui.pages.NftGenerationPage;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NftGenerationSteps {
    private final NftGenerationPage nftGenerationPage;
    private final MetamaskFlow metamaskFlow;
    private final EventMapper eventMapper;

    public NftGenerationSteps() {
        nftGenerationPage = new NftGenerationPage();
        metamaskFlow = new MetamaskFlow();
        eventMapper = new EventMapper();
    }

    public void createCollectionWithData(CreateCollectionFormDto data) {
        nftGenerationPage.createCollectionWithData(data);
        metamaskFlow.approveTransaction();
    }

    public void createNftWithData(CreateNftFormDto data) {
        nftGenerationPage.createNftWithData(data);
        metamaskFlow.approveTransaction();
    }

    public void verifyCollectionIsCreated(CreateCollectionFormDto data) {
        EventDto expectedResult = eventMapper.map(data);
        List<EventDto> actualEvents = nftGenerationPage.collectEvents();
        assertThat(actualEvents).hasSizeGreaterThan(0);
        EventDto actualEvent = actualEvents.stream()
                .filter(event -> event.getName() != null && event.getName().equals(expectedResult.getName()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("There is no event with name: " + expectedResult.getName()));
        assertThat(actualEvent).usingComparator(getCollectionCreationEventComparator()).isEqualTo(expectedResult);
    }

    public void verifyNftIsCreated(CreateNftFormDto data) {
        EventDto expectedEvent = eventMapper.map(data);
        List<EventDto> actualEvents = nftGenerationPage.collectEvents();
        assertThat(actualEvents).hasSizeGreaterThan(0);
        EventDto actualEvent = actualEvents.stream()
                .filter(event -> event.getTokenURI() != null && event.getTokenURI().endsWith(String.valueOf(expectedEvent.getTokenId())))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("There is no event with tokenId: " + expectedEvent.getTokenId()));
        assertThat(actualEvent).usingComparator(getNftCreationEventComparator()).isEqualTo(expectedEvent);
    }

    private Comparator<EventDto> getCollectionCreationEventComparator() {
        return ((first, second) -> {
            if (first.getName().equals(second.getName()) &&
                first.getSymbol().equals(second.getSymbol())){
                return 0;
            } else {
                return -1;
            }
        });
    }

    private Comparator<EventDto> getNftCreationEventComparator() {
        return ((first, second) -> {
            if (first.getTokenURI().endsWith(String.valueOf(second.getTokenId())) &&
                first.getTokenId().equals(second.getTokenId()) &&
                first.getCollection().equals(second.getCollection()) &&
                first.getRecipient().equals(second.getRecipient())){
                return 0;
            } else {
                return -1;
            }
        });
    }
}
