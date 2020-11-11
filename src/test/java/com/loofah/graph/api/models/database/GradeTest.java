package com.loofah.graph.api.models.database;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.loofah.graph.api.helpers.TestHelpers.getDefaultGradeBuilder;
import static org.junit.Assert.assertEquals;

class GradeTest {

    @Test
    void testSorting() {
        // Given
        final Grade analystDeveloper = getDefaultGradeBuilder().withTitle("analyst developer").build();
        final Grade developer = getDefaultGradeBuilder().withTitle("developer").build();
        final Grade seniorDeveloper = getDefaultGradeBuilder().withTitle("senior developer").build();
        final Grade technicalLead = getDefaultGradeBuilder().withTitle("technical lead").build();
        final Grade seniorTechnicalLead = getDefaultGradeBuilder().withTitle("senior technical lead").build();
        final Grade technicalDirector = getDefaultGradeBuilder().withTitle("technical director").build();
        final Grade partner = getDefaultGradeBuilder().withTitle("partner").build();
        final Grade unrecognised1 = getDefaultGradeBuilder().withTitle("unrecognised1").build();
        final Grade unrecognised2 = getDefaultGradeBuilder().withTitle("unrecognised2").build();
        final Grade nullTitle = getDefaultGradeBuilder().withTitle(null).build();

        final List<Grade> grades = Arrays.asList(seniorDeveloper, seniorTechnicalLead, developer, technicalLead, unrecognised2, nullTitle, unrecognised1, analystDeveloper, partner, technicalDirector);
        final List<Grade> expectedSort = Arrays.asList(analystDeveloper, developer, seniorDeveloper, technicalLead, seniorTechnicalLead, technicalDirector, partner, unrecognised1, unrecognised2, nullTitle);

        // When
        final List<Grade> actualSort = grades.stream().sorted().collect(Collectors.toList());

        // Then
        assertEquals(expectedSort, actualSort);
    }

}