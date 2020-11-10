package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.services.CraftService;
import graphql.schema.DataFetchingEnvironment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.loofah.graph.api.helpers.TestHelpers.CRAFT_ID_VALUE_1;
import static com.loofah.graph.api.helpers.TestHelpers.getDefaultCraftBuilder;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CraftQueryTest {

    @Mock
    private CraftService craftService;

    @Mock
    private DataFetchingEnvironment dataFetchingEnvironment;

    @InjectMocks
    private CraftQuery craftQuery;

    @Test
    public void get_findsCraftFromRepositoryWithGivenId() {
        final Craft expectedCraft = getDefaultCraftBuilder().build();

        when(dataFetchingEnvironment.getArgument("id")).thenReturn(CRAFT_ID_VALUE_1);
        when(craftService.getById(CRAFT_ID_VALUE_1)).thenReturn(expectedCraft);

        final Craft actualCraft = craftQuery.get(dataFetchingEnvironment);
        assertEquals(expectedCraft, actualCraft);
    }
}
