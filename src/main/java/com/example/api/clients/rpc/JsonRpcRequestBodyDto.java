package com.example.api.clients.rpc;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JsonRpcRequestBodyDto {
    private String method;
    private String[] params;
    private Integer id;
    private String jsonrpc;
}
