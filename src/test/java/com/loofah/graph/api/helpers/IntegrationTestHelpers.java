package com.loofah.graph.api.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.loofah.graph.api.models.dto.SkillDTO;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.loofah.graph.api.helpers.IntegrationTestConstants.DATA;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


public class IntegrationTestHelpers {

    public static void assertResponseHasNullData(ObjectMapper objectMapper, ResponseEntity<String> response) throws JsonProcessingException {

        assertNotNull("response body should not be null",response.getBody());
        final JsonNode responseData = objectMapper.readTree(response.getBody()).get(DATA);

        assertNull(responseData.get(0));
    }

    public static void assertResponseHasErrorMessage(ObjectMapper objectMapper, ResponseEntity<String> response, String expectedErrorMessage) throws JsonProcessingException {

        assertNotNull("response body should not be null",response.getBody());
        final JsonNode responseError = objectMapper.readTree(response.getBody()).get("errors");

        assertNotNull("response error should not be null",responseError.get(0));
        final TextNode responseErrorMessage = (TextNode) responseError.get(0).get("message");

        assertEquals(expectedErrorMessage, responseErrorMessage.asText());
    }

    public static void assertSkillHasCategoryWithTitle(LinkedHashMap skill, String expectedCategoryTitle){
        LinkedHashMap categoryOnSkill = (LinkedHashMap) skill.get(SkillDTO.SkillDTOFields.CATEGORY.key());
        assertEquals(expectedCategoryTitle, categoryOnSkill.get("title"));
    }

    public static void assertSkillHasGradeWithTitles(LinkedHashMap skill, List<String> expectedGradeTitles){
        LinkedHashMap gradeOnSkill = (LinkedHashMap) skill.get(SkillDTO.SkillDTOFields.GRADE.key());
        assertThat(gradeOnSkill.get("title")).isIn(expectedGradeTitles);
    }

    public static void assertSkillHasCraftsWithTitles(LinkedHashMap skill, List<String> expectedCraftTitles){
        List<LinkedHashMap> craftsOnSkill = (List<LinkedHashMap>) skill.get(SkillDTO.SkillDTOFields.CRAFTS.key());
        List<String> craftTitlesOnSkill = craftsOnSkill.stream().map(craft -> (String) craft.get("title")).collect(Collectors.toList());
        assertThat(craftTitlesOnSkill).containsAnyElementsOf(expectedCraftTitles);
    }
}
