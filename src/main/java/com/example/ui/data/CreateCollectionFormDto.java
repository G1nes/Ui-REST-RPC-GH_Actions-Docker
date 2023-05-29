package com.example.ui.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCollectionFormDto {

    private String collectionName;
    private String collectionSymbol;
    private String collectionTokenUri;
}
