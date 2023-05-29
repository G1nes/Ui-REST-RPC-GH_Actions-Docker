package com.example.ui.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateNftFormDto {

    private String collectionAddress;
    private String recipientAddress;
    private Long tokenId;
}
