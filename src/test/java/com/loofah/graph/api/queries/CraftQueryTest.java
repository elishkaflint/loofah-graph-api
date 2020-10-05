package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.repositories.CraftRepository;
import graphql.schema.DataFetchingEnvironment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.loofah.graph.api.helpers.TestHelpers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CraftQueryTest {

    @Mock
    private CraftRepository craftRepository;

    @Mock
    private DataFetchingEnvironment dataFetchingEnvironment;

    private CraftQuery craftQuery;

    @Before
    public void setUp() {
        craftQuery = new CraftQuery(craftRepository);
    }

    @Test
    public void get_findsCraftFromRepositoryWithGivenId() {
        final Craft expectedCraft = getDefaultCraftBuilder().build();

        when(dataFetchingEnvironment.getArgument("id")).thenReturn(CRAFT_ID);
        when(craftRepository.findById(CRAFT_ID)).thenReturn(Optional.of(expectedCraft));

        final Craft actualCraft = craftQuery.get(dataFetchingEnvironment);
        assertEquals(expectedCraft, actualCraft);
    }
}
