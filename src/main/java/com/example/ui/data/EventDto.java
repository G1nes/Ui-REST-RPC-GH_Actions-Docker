package com.example.ui.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventDto {

    private String address;
    private String name;
    private String symbol;
    private String collection;
    private String recipient;
    private String tokenURI;
    private Long tokenId;
}
