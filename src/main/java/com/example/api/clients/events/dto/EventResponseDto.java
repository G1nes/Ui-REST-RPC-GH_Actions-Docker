package com.example.api.clients.events.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventResponseDto {

    private String collection;
    private String name;
    private String symbol;
    private String eventName;
    private String recipient;
    private Long tokenId;
    private String tokenURI;
}
