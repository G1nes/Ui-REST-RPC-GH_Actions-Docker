package com.example.api.clients.events;

import com.example.api.clients.BaseClient;
import com.example.api.clients.events.dto.EventResponseDto;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class EventClient extends BaseClient {

    public List<EventResponseDto> pollEventsUntil(Predicate<EventResponseDto> condition) {
        pollUntil(() -> getEvents()
                .stream()
                .anyMatch(condition));
        return getEvents();
    }

    private List<EventResponseDto> getEvents() {
        return Arrays.asList(get("/events", EventResponseDto[].class));
    }
}
