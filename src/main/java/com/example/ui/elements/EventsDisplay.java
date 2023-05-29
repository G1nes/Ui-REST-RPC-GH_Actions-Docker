package com.example.ui.elements;

import com.codeborne.selenide.ElementsCollection;
import com.example.ui.data.EventDto;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.example.ui.util.StringFinder.getSubstringValueByKey;

public class EventsDisplay {

    private final ElementsCollection events;

    public EventsDisplay() {
        events = $$x("//b[contains(text(), 'Events')]/following-sibling::div//div[@class='list-group-item']");
    }

    public List<EventDto> collectEvents() {
        //event example: "Collection Created with address: 0xd4c0f12c0f44F52D9A152Fd02b10a76FD3234f7b, name: TEST and symbol: ASD"
        //event example: "NFT minted for collection: 0x65e8518738f007C203Eb09c9323d3b593EeeF00C, to: 0x403e8eec9Fa7896FC25462adE64daDeEAA4cD67A, token id: 28244 and token URI: collection_token_uri_ef1901981347fe166d0c28244"
        return events.asDynamicIterable()
                .stream()
                .map(event -> {
                    String text = event.getText();
                    if (text.startsWith("Collection Created")){
                        return createCollectionCreatedEventFromString(text);
                    } else if (text.startsWith("NFT minted")) {
                        return createNftCreatedEventFromString(text);
                    } else {
                        return EventDto.builder()
                                .build();
                    }
                })
                .collect(Collectors.toList());
    }

    private EventDto createCollectionCreatedEventFromString(String source) {
        return EventDto.builder()
                .address(getSubstringValueByKey(source, "address: ", ","))
                .name(getSubstringValueByKey(source, "name: ", " and"))
                .symbol(getSubstringValueByKey(source, "symbol: ", ""))
                .build();
    }

    private EventDto createNftCreatedEventFromString(String source) {
        return EventDto.builder()
                .collection(getSubstringValueByKey(source, "collection: ", ","))
                .recipient(getSubstringValueByKey(source, "to: ", ","))
                .tokenId(Long.parseLong(getSubstringValueByKey(source, "token id: ", " and")))
                .tokenURI(getSubstringValueByKey(source, "token URI:", ""))
                .build();
    }
}
