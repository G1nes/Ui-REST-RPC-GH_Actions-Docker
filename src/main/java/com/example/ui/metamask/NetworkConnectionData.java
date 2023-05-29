package com.example.ui.metamask;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NetworkConnectionData {

    private String networkName;
    private String rpcUrl;
    private String chainId;
    private String currencySymbol;
}
