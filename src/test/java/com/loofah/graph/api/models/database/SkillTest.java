package com.loofah.graph.api.models.database;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.loofah.graph.api.helpers.TestHelpers.getDefaultCategoryBuilder;
import static com.loofah.graph.api.helpers.TestHelpers.getDefaultGradeBuilder;
import static org.junit.Assert.assertEquals;

class SkillTest {

    @Test
    void testSorting() {
        // Given
        final Grade analystDeveloper = getDefaultGradeBuilder().withTitle("analyst developer").build();
        final Grade seniorDeveloper = getDefaultGradeBuilder().withTitle("senior developer").build();

        final Category aardvark = getDefaultCategoryBuilder().withTitle("aardvark").build();
        final Category bear = getDefaultCategoryBuilder().withTitle("bear").build();

        final String agile = "agile";
        final String bootstrap = "bootstrap";

        final Skill first = Skill.builder().withGrade(analystDeveloper).withCategory(aardvark).withTitle(agile).build();
        final Skill second = Skill.builder().withGrade(analystDeveloper).withCategory(aardvark).withTitle(bootstrap).build();
        final Skill third = Skill.builder().withGrade(analystDeveloper).withCategory(aardvark).withTitle(null).build();
        final Skill fourth = Skill.builder().withGrade(analystDeveloper).withCategory(bear).withTitle(agile).build();
        final Skill fifth = Skill.builder().withGrade(analystDeveloper).withCategory(bear).withTitle(bootstrap).build();
        final Skill sixth = Skill.builder().withGrade(analystDeveloper).withCategory(null).withTitle(null).build();
        final Skill seventh = Skill.builder().withGrade(seniorDeveloper).withCategory(aardvark).withTitle(agile).build();
        final Skill eighth = Skill.builder().withGrade(seniorDeveloper).withCategory(aardvark).withTitle(bootstrap).build();
        final Skill ninth = Skill.builder().withGrade(seniorDeveloper).withCategory(bear).withTitle(agile).build();
        final Skill tenth = Skill.builder().withGrade(seniorDeveloper).withCategory(bear).withTitle(bootstrap).build();
        final Skill eleventh = Skill.builder().withGrade(null).withCategory(null).withTitle(null).build();


        final List<Skill> skills = Arrays.asList(third, eleventh, seventh, second, fifth, eighth, tenth, first, ninth, fourth, sixth);
        final List<Skill> expectedSort = Arrays.asList(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh);

        // When
        final List<Skill> actualSort = skills.stream().sorted().collect(Collectors.toList());

        // Then
        assertEquals(expectedSort, actualSort);
    }

}