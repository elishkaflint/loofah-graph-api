package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.repositories.CategoryRepository;
import graphql.schema.DataFetchingEnvironment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    private CategoryRepository categoryRepository;

    @Mock
    private DataFetchingEnvironment dataFetchingEnvironment;

    private CategoriesQuery categoriesQuery;

    @Before
    public void setUp() throws Exception {
        categoriesQuery = new CategoriesQuery(categoryRepository);
    }

    @Test
    public void get_findsAllCategoriesFromRepository() {

        final List<Category> expectedCategories = Collections.singletonList(
                getDefaultCategoryBuilder().build()
        );
        when(categoryRepository.findAll()).thenReturn(expectedCategories);

        final List<Category> actualCategories = categoriesQuery.get(dataFetchingEnvironment);

        assertEquals(expectedCategories, actualCategories);
    }


}