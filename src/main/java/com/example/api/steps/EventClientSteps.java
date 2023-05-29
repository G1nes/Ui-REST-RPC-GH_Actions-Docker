package com.example.api.steps;

import com.example.api.clients.events.EventClient;
import com.example.api.clients.events.dto.EventResponseDto;
import com.example.api.mapper.EventMapper;
import com.example.ui.data.CreateCollectionFormDto;
import com.example.ui.data.CreateNftFormDto;

import java.util.Comparator;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

public class EventClientSteps {

    private final EventClient eventClient = new EventClient();
    private final EventMapper eventMapper = new EventMapper();

    public String getCollectionAddressByName(String name) {
        Predicate<EventResponseDto> condition = event -> event.getName() != null && event.getName().equals(name);
        return eventClient.pollEventsUntil(condition)
                .stream()
                .filter(condition)
                .map(EventResponseDto::getCollection)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("There is no collection with name " + name));
    }

    public void verifyEventsContainsCreatedEvent(CreateCollectionFormDto data) {
        EventResponseDto expectedEvent = eventMapper.map(data);
        Predicate<EventResponseDto> condition = event -> event.getName() != null &&
                                                         expectedEvent.getName().equals(event.getName());
        EventResponseDto actualCollectionCreatedEvent = eventClient.pollEventsUntil(condition)
                .stream()
                .filter(condition)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("There is no collection creation event with name " +
                                                             expectedEvent.getName()));
        assertThat(actualCollectionCreatedEvent).usingComparator(getCollectionCreationEventComparator()).isEqualTo(
                expectedEvent);
        assertThat(actualCollectionCreatedEvent.getCollection()).isNotEmpty();
    }

    public void verifyEventsContainsCreatedEvent(CreateNftFormDto data) {
        EventResponseDto expectedEvent = eventMapper.map(data);
        Predicate<EventResponseDto> condition = event ->
                event.getTokenURI() != null &&
                event.getTokenURI().endsWith(String.valueOf(expectedEvent.getTokenId()));
        EventResponseDto actualNftCreatedEvent = eventClient.pollEventsUntil(condition)
                .stream()
                .filter(condition)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("There is no nft creation event with tokenId " +
                                                             expectedEvent.getTokenId()));
        assertThat(actualNftCreatedEvent).usingComparator(getNftCreationEventComparator()).isEqualTo(expectedEvent);
        assertThat(actualNftCreatedEvent.getTokenURI()).isNotEmpty();
    }

    private Comparator<EventResponseDto> getCollectionCreationEventComparator() {
        return ((first, second) -> {
            if (first.getName().equals(second.getName()) &&
                first.getSymbol().equals(second.getSymbol()) &&
                first.getEventName().equals(second.getEventName())) {
                return 0;
            } else {
                return -1;
            }
        });
    }

    private Comparator<EventResponseDto> getNftCreationEventComparator() {
        return ((first, second) -> {
            if (first.getTokenURI().endsWith(String.valueOf(second.getTokenId())) &&
                first.getCollection().equals(second.getCollection()) &&
                first.getRecipient().equals(second.getRecipient()) &&
                first.getEventName().equals(second.getEventName())) {
                return 0;
            } else {
                return -1;
            }
        });
    }
}
