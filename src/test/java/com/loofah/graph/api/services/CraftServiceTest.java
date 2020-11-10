package com.loofah.graph.api.services;

import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.retrievers.DataRetriever;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CraftServiceTest {

    @Mock
    private DataRetriever dataRetriever;

    @InjectMocks
    private CraftService craftService;

    @Test
    public void getById() {

        Craft expectedCraft = Craft.builder().build();
        when(dataRetriever.getCraftById("id")).thenReturn(expectedCraft);

        Craft actualCraft = craftService.getById("id");

        assertEquals(expectedCraft, actualCraft);
    }

    @Test
    public void getAll() {

        List<Craft> expectedCrafts = Collections.singletonList(Craft.builder().build());
        when(dataRetriever.getAllCrafts()).thenReturn(expectedCrafts);

        List<Craft> actualCrafts = craftService.getAll();

        assertEquals(expectedCrafts, actualCrafts);
    }

}