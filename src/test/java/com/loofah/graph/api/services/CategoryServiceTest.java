package com.loofah.graph.api.services;

import com.loofah.graph.api.exceptions.DataNotFoundException;
import com.loofah.graph.api.models.database.Category;
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
public class CategoryServiceTest {

    @Mock
    private DataRetriever dataRetriever;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void getById_whenCategoryExists_thenReturnCategory() {

        Category expectedCategory = Category.builder().build();
        when(dataRetriever.getCategoryById("1")).thenReturn(Optional.of(expectedCategory));

        Category actualCategory = categoryService.getById("1");

        assertEquals(expectedCategory, actualCategory);
    }

    @Test(expected = DataNotFoundException.class)
    public void getById_whenCategoryDoesNotExist_thenThrowException() {

        when(dataRetriever.getCategoryById("0")).thenReturn(Optional.empty());
        categoryService.getById("0");
    }

    @Test
    public void getAll() {

        List<Category> expectedCategories = Collections.singletonList(Category.builder().build());
        when(dataRetriever.getAllCategories()).thenReturn(expectedCategories);

        List<Category> actualCategory = categoryService.getAll();

        assertEquals(expectedCategories, actualCategory);
    }
}