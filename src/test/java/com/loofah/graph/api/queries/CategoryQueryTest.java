package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.services.CategoryService;
import graphql.schema.DataFetchingEnvironment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.loofah.graph.api.helpers.TestHelpers.CATEGORY_ID;
import static com.loofah.graph.api.helpers.TestHelpers.getDefaultCategoryBuilder;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryQueryTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private DataFetchingEnvironment dataFetchingEnvironment;

    @InjectMocks
    private CategoryQuery categoryQuery;

    @Test
    public void get_findsCategoryFromRepositoryWithGivenId() {
        final Category expectedCategory = getDefaultCategoryBuilder().build();

        when(dataFetchingEnvironment.getArgument("id")).thenReturn(CATEGORY_ID);
        when(categoryService.getById(CATEGORY_ID)).thenReturn(expectedCategory);

        final Category actualCategory = categoryQuery.get(dataFetchingEnvironment);
        assertEquals(expectedCategory, actualCategory);
    }
}
