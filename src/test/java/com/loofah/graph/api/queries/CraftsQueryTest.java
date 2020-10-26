package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.services.CraftService;
import graphql.schema.DataFetchingEnvironment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static com.loofah.graph.api.helpers.TestHelpers.getDefaultCraftBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CraftsQueryTest {

    @Mock
    private CraftService craftService;

    @Mock
    private DataFetchingEnvironment dataFetchingEnvironment;

    @InjectMocks
    private CraftsQuery craftsQuery;

    @Test
    public void get_findsAllCraftsFromRepository() {

        final List<Craft> expectedCrafts = Collections.singletonList(
                getDefaultCraftBuilder().build()
        );
        when(craftService.getAll()).thenReturn(expectedCrafts);

        final List<Craft> actualCrafts = craftsQuery.get(dataFetchingEnvironment);

        assertEquals(expectedCrafts, actualCrafts);
    }


}