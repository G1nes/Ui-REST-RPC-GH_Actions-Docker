package com.example.api.clients.events.enums;

import lombok.Getter;

public enum EventType {
    COLLECTION_CREATED("CollectionCreated"),
    TOKEN_MINTED("TokenMinted");

    EventType(String value) {
        this.value = value;
    }

    @Getter
    private final String value;
}
