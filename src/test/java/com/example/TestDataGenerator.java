package com.example;

import com.example.ui.data.CreateCollectionFormDto;
import com.example.ui.data.CreateNftFormDto;
import com.github.javafaker.Faker;

public class TestDataGenerator {

    private final Faker faker = Faker.instance();

    public CreateNftFormDto generateNftCreationFormData(String collectionAddress) {
        return CreateNftFormDto.builder()
                .collectionAddress(collectionAddress)
                .recipientAddress(System.getenv("WALLET_ADDRESS"))
                .tokenId((long) faker.number().numberBetween(0, 9999))
                .build();
    }

    public CreateCollectionFormDto generateCollectionCreationFormRandomData() {
        return CreateCollectionFormDto.builder()
                .collectionName(faker.random().hex())
                .collectionSymbol(faker.random().hex(3))
                .collectionTokenUri(faker.random().hex(5))
                .build();
    }
}
