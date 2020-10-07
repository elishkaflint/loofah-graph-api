package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.repositories.CraftRepository;
import graphql.schema.DataFetchingEnvironment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    private CraftRepository craftRepository;

    @Mock
    private DataFetchingEnvironment dataFetchingEnvironment;

    private CraftsQuery craftsQuery;

    @Before
    public void setUp() throws Exception {
        craftsQuery = new CraftsQuery(craftRepository);
    }

    @Test
    public void get_findsAllCraftsFromRepository() {

        final List<Craft> expectedCrafts = Collections.singletonList(
                getDefaultCraftBuilder().build()
        );
        when(craftRepository.findAll()).thenReturn(expectedCrafts);

        final List<Craft> actualCrafts = craftsQuery.get(dataFetchingEnvironment);

        assertEquals(expectedCrafts, actualCrafts);
    }


}