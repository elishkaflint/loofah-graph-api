package com.loofah.graph.api.models.database;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.loofah.graph.api.helpers.TestHelpers.getDefaultCategoryBuilder;
import static org.junit.Assert.assertEquals;

class CategoryTest {

    @Test
    void testSorting() {
        // Given

        final Category apple = getDefaultCategoryBuilder().withTitle("apple").build();
        final Category blackberry = getDefaultCategoryBuilder().withTitle("blackberry").build();
        final Category blackcurrant = getDefaultCategoryBuilder().withTitle("blackcurrant").build();
        final Category clementine = getDefaultCategoryBuilder().withTitle("clementine").build();
        final Category nullberry = getDefaultCategoryBuilder().withTitle(null).build();

        final List<Category> categories = Arrays.asList(clementine, blackberry, apple, nullberry, blackcurrant);
        final List<Category> expectedSort = Arrays.asList(apple, blackberry, blackcurrant, clementine, nullberry);

        // When
        final List<Category> actualSort = categories.stream().sorted().collect(Collectors.toList());

        // Then
        assertEquals(expectedSort, actualSort);
    }
}