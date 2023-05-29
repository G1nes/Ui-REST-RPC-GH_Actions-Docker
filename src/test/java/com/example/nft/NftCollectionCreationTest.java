package com.example.nft;

import com.example.BaseTest;
import com.example.api.steps.EventClientSteps;
import com.example.ui.data.CreateCollectionFormDto;
import com.example.ui.steps.NftGenerationSteps;
import org.junit.jupiter.api.Test;

public class NftCollectionCreationTest extends BaseTest {

    private final NftGenerationSteps nftGenerationSteps = new NftGenerationSteps();
    private final EventClientSteps eventClientSteps = new EventClientSteps();

    @Test
    public void testCreateCollection() {
        //Given
        CreateCollectionFormDto collectionData = generator.generateCollectionCreationFormRandomData();
        //When
        nftGenerationSteps.createCollectionWithData(collectionData);
        //Then
        eventClientSteps.verifyEventsContainsCreatedEvent(collectionData);
        nftGenerationSteps.verifyCollectionIsCreated(collectionData);
    }
}
