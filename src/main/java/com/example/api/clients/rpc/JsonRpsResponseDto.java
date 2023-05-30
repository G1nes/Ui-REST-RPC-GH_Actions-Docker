package com.example.api.clients.rpc;

import lombok.Data;

@Data
public class JsonRpsResponseDto {
    private String jsonrpc;
    private Long id;
    private String result;
}
