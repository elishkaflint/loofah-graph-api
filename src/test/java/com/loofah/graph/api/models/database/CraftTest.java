package com.loofah.graph.api.models.database;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.loofah.graph.api.helpers.TestHelpers.getDefaultCraftBuilder;
import static org.junit.Assert.assertEquals;

class CraftTest {

    @Test
    void testSorting() {
        // Given

        final Craft apple = getDefaultCraftBuilder().withTitle("apple").build();
        final Craft blackberry = getDefaultCraftBuilder().withTitle("blackberry").build();
        final Craft blackcurrant = getDefaultCraftBuilder().withTitle("blackcurrant").build();
        final Craft clementine = getDefaultCraftBuilder().withTitle("clementine").build();
        final Craft nullberry = getDefaultCraftBuilder().withTitle(null).build();

        final List<Craft> crafts = Arrays.asList(clementine, blackberry, apple, nullberry, blackcurrant);
        final List<Craft> expectedSort = Arrays.asList(apple, blackberry, blackcurrant, clementine, nullberry);

        // When
        final List<Craft> actualSort = crafts.stream().sorted().collect(Collectors.toList());

        // Then
        assertEquals(expectedSort, actualSort);
    }

}