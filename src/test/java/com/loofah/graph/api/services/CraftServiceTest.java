package com.loofah.graph.api.services;

import com.loofah.graph.api.exceptions.DataNotFoundException;
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
    public void getById_whenCraftExists_thenReturnCraft() {

        Craft expectedCraft = Craft.builder().build();
        when(dataRetriever.getCraftById("id")).thenReturn(Optional.of(expectedCraft));

        Craft actualCraft = craftService.getById("id");

        assertEquals(expectedCraft, actualCraft);
    }

    @Test(expected = DataNotFoundException.class)
    public void getById_whenCraftDoesNotExist_thenThrowDataNotFoundException() {

        when(dataRetriever.getCraftById("0")).thenReturn(Optional.empty());
        craftService.getById("0");
    }

    @Test
    public void getAll() {

        List<Craft> expectedCrafts = Collections.singletonList(Craft.builder().build());
        when(dataRetriever.getAllCrafts()).thenReturn(expectedCrafts);

        List<Craft> actualCrafts = craftService.getAll();

        assertEquals(expectedCrafts, actualCrafts);
    }

}