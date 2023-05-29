package com.example.nft;

import com.example.BaseTest;
import com.example.api.steps.EventClientSteps;
import com.example.ui.data.CreateCollectionFormDto;
import com.example.ui.data.CreateNftFormDto;
import com.example.ui.steps.NftGenerationSteps;
import org.junit.jupiter.api.Test;

public class NftCreationTest extends BaseTest {

    private final NftGenerationSteps nftGenerationSteps = new NftGenerationSteps();
    private final EventClientSteps eventClientSteps = new EventClientSteps();

    @Test
    public void testCreateNft() {
        //Given
        CreateCollectionFormDto collectionData = generator.generateCollectionCreationFormRandomData();
        nftGenerationSteps.createCollectionWithData(collectionData);
        String collectionName = collectionData.getCollectionName();
        String collectionAddress = eventClientSteps.getCollectionAddressByName(collectionName);
        CreateNftFormDto nftData = generator.generateNftCreationFormData(collectionAddress);
        //When
        nftGenerationSteps.createNftWithData(nftData);
        //Then
        eventClientSteps.verifyEventsContainsCreatedEvent(nftData);
        nftGenerationSteps.verifyNftIsCreated(nftData);
    }
}
