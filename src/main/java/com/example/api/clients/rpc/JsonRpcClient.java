package com.example.api.clients.rpc;

import com.example.api.clients.BaseClient;
import com.example.api.configuration.RpcConfiguration;
import io.restassured.http.ContentType;
import org.aeonbits.owner.ConfigFactory;

import java.util.Map;

public class JsonRpcClient extends BaseClient {

    private static final RpcConfiguration RPC_CONFIGURATION = ConfigFactory.create(RpcConfiguration.class, Map.of());
    private static final String TOKEN = System.getenv("RPC_TOKEN");
    private static final String URL = RPC_CONFIGURATION + TOKEN;

    public JsonRpsResponseDto getChainId(){
        JsonRpcRequestBodyDto requestBody = JsonRpcRequestBodyDto.builder()
                .id(1)
                .jsonrpc(JsonRpcVersionType.SECOND.getVersion())
                .params(new String[0])
                .method("eth_chainId")
                .build();
        return post(requestBody);
    }

    private JsonRpsResponseDto post(JsonRpcRequestBodyDto body) {
        return post(URL,
                    body,
                    ContentType.JSON,
                    JsonRpsResponseDto.class);
    }
}
