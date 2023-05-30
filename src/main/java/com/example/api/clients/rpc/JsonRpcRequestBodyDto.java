package com.example.api.clients.rpc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JsonRpcRequestBodyDto {
    private String method;
    private String[] params;
    private Integer id;
    @JsonProperty("jsonrpc")
    private String jsonRpc;
}
