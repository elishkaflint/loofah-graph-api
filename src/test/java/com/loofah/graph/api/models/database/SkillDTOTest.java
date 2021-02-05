package com.loofah.graph.api.models.database;

import com.loofah.graph.api.models.dto.SkillDTO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.loofah.graph.api.helpers.TestHelpers.getDefaultCategoryBuilder;
import static com.loofah.graph.api.helpers.TestHelpers.getDefaultGradeBuilder;
import static org.junit.Assert.assertEquals;

class SkillDTOTest {

    @Test
    void testSorting() {
        // Given
        final Grade analystDeveloper = getDefaultGradeBuilder().withTitle("analystDeveloper").build();
        final Grade seniorDeveloper = getDefaultGradeBuilder().withTitle("seniorDeveloper").build();

        final Category aardvark = getDefaultCategoryBuilder().withTitle("aardvark").build();
        final Category bear = getDefaultCategoryBuilder().withTitle("bear").build();

        final String agile = "agile";
        final String bootstrap = "bootstrap";

        final SkillDTO first = SkillDTO.builder().withGrade(analystDeveloper).withCategory(aardvark).withTopic(agile).build();
        final SkillDTO second = SkillDTO.builder().withGrade(analystDeveloper).withCategory(aardvark).withTopic(bootstrap).build();
        final SkillDTO third = SkillDTO.builder().withGrade(analystDeveloper).withCategory(aardvark).withTopic(null).build();
        final SkillDTO fourth = SkillDTO.builder().withGrade(analystDeveloper).withCategory(bear).withTopic(agile).build();
        final SkillDTO fifth = SkillDTO.builder().withGrade(analystDeveloper).withCategory(bear).withTopic(bootstrap).build();
        final SkillDTO sixth = SkillDTO.builder().withGrade(analystDeveloper).withCategory(null).withTopic(null).build();
        final SkillDTO seventh = SkillDTO.builder().withGrade(seniorDeveloper).withCategory(aardvark).withTopic(agile).build();
        final SkillDTO eighth = SkillDTO.builder().withGrade(seniorDeveloper).withCategory(aardvark).withTopic(bootstrap).build();
        final SkillDTO ninth = SkillDTO.builder().withGrade(seniorDeveloper).withCategory(bear).withTopic(agile).build();
        final SkillDTO tenth = SkillDTO.builder().withGrade(seniorDeveloper).withCategory(bear).withTopic(bootstrap).build();
        final SkillDTO eleventh = SkillDTO.builder().withGrade(null).withCategory(null).withTopic(null).build();


        final List<SkillDTO> skills = Arrays.asList(third, eleventh, seventh, second, fifth, eighth, tenth, first, ninth, fourth, sixth);
        final List<SkillDTO> expectedSort = Arrays.asList(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh);

        // When
        final List<SkillDTO> actualSort = skills.stream().sorted().collect(Collectors.toList());

        // Then
        assertEquals(expectedSort, actualSort);
    }

}