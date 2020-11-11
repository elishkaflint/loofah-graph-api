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

        final Category aardvark = getDefaultCategoryBuilder().withTitle("aardvark").build();
        final Category bear = getDefaultCategoryBuilder().withTitle("bear").build();
        final Category butterfly = getDefaultCategoryBuilder().withTitle("butterfly").build();
        final Category cat = getDefaultCategoryBuilder().withTitle("cat").build();
        final Category nullosaurus = getDefaultCategoryBuilder().withTitle(null).build();

        final List<Category> categories = Arrays.asList(cat, bear, aardvark, nullosaurus, butterfly);
        final List<Category> expectedSort = Arrays.asList(aardvark, bear, butterfly, cat, nullosaurus);

        // When
        final List<Category> actualSort = categories.stream().sorted().collect(Collectors.toList());

        // Then
        assertEquals(expectedSort, actualSort);
    }
}