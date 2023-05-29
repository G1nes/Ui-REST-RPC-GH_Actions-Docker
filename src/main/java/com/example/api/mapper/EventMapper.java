package com.example.api.mapper;

import com.example.api.clients.events.dto.EventResponseDto;
import com.example.api.clients.events.enums.EventType;
import com.example.ui.data.CreateCollectionFormDto;
import com.example.ui.data.CreateNftFormDto;

public class EventMapper {
    public EventResponseDto map(CreateCollectionFormDto data) {
        return EventResponseDto.builder()
                .name(data.getCollectionName())
                .symbol(data.getCollectionSymbol())
                .collection("")
                .eventName(EventType.COLLECTION_CREATED.getValue())
                .build();
    }

    public EventResponseDto map(CreateNftFormDto data) {
        return EventResponseDto.builder()
                .collection(data.getCollectionAddress())
                .recipient(data.getRecipientAddress())
                .tokenId(data.getTokenId())
                .tokenURI("")
                .eventName(EventType.TOKEN_MINTED.getValue())
                .build();
    }
}
