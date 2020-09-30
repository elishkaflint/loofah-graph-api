package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.repositories.CategoryRepository;
import graphql.schema.DataFetchingEnvironment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.loofah.graph.api.helpers.TestHelpers.CATEGORY_ID;
import static com.loofah.graph.api.helpers.TestHelpers.getDefaultCategoryBuilder;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryQueryTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private DataFetchingEnvironment dataFetchingEnvironment;

    private CategoryQuery categoryQuery;

    @Before
    public void setUp() {
        categoryQuery = new CategoryQuery(categoryRepository);
    }

    @Test
    public void get_findsCategoryFromRepositoryWithGivenId() {
        Category expectedCategory = getDefaultCategoryBuilder().build();

        when(dataFetchingEnvironment.getArgument("id")).thenReturn(CATEGORY_ID);
        when(categoryRepository.findById(CATEGORY_ID)).thenReturn(Optional.of(expectedCategory));

        Category actualCategory = categoryQuery.get(dataFetchingEnvironment);
        assertEquals(expectedCategory, actualCategory);
    }
}
