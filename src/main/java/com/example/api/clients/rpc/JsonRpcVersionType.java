package com.example.api.clients.rpc;

import lombok.Getter;

public enum JsonRpcVersionType {
    SECOND("2.0");

    JsonRpcVersionType(String version) {
        this.version = version;
    }

    @Getter
    private final String version;
}
