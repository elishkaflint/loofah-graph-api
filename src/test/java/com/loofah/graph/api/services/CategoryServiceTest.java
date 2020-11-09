package com.loofah.graph.api.services;

import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.repositories.CategoryRepository;
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
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void getById() {

        Category expectedCategory = Category.builder().build();
        when(categoryRepository.findById("id")).thenReturn(Optional.of(expectedCategory));

        Category actualCategory = categoryService.getById("id");

        assertEquals(expectedCategory, actualCategory);
    }

    @Test
    public void getAll() {

        List<Category> expectedCategories = Collections.singletonList(Category.builder().build());
        when(categoryRepository.findAll()).thenReturn(expectedCategories);

        List<Category> actualCategory = categoryService.getAll();

        assertEquals(expectedCategories, actualCategory);
    }
}