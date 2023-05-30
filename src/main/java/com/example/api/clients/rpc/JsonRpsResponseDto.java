package com.example.api.clients.rpc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonRpsResponseDto {
    private String jsonrpc;
    private Long id;
    private String result;
}
