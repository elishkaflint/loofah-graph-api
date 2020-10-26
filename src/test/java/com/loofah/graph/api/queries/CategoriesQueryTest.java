package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.services.CategoryService;
import graphql.schema.DataFetchingEnvironment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static com.loofah.graph.api.helpers.TestHelpers.getDefaultCategoryBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoriesQueryTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private DataFetchingEnvironment dataFetchingEnvironment;

    @InjectMocks
    private CategoriesQuery categoriesQuery;

    @Test
    public void get_findsAllCategoriesFromRepository() {

        final List<Category> expectedCategories = Collections.singletonList(
                getDefaultCategoryBuilder().build()
        );
        when(categoryService.getAll()).thenReturn(expectedCategories);

        final List<Category> actualCategories = categoriesQuery.get(dataFetchingEnvironment);

        assertEquals(expectedCategories, actualCategories);
    }


}