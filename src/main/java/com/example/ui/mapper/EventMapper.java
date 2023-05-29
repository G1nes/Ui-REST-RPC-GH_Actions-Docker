package com.example.ui.mapper;

import com.example.ui.data.*;

public class EventMapper {
    public EventDto map(CreateCollectionFormDto data) {
        return EventDto.builder()
                .address("")
                .name(data.getCollectionName())
                .symbol(data.getCollectionSymbol())
                .build();
    }

    public EventDto map(CreateNftFormDto data) {
        return EventDto.builder()
                .collection(data.getCollectionAddress())
                .recipient(data.getRecipientAddress())
                .tokenId(data.getTokenId())
                .tokenURI("")
                .build();
    }
}
