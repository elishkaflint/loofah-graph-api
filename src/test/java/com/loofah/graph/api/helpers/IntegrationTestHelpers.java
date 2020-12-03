package com.loofah.graph.api.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.loofah.graph.api.models.DTO.SkillDTO;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;

import static com.loofah.graph.api.helpers.IntegrationTestConstants.DATA;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
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

    public static void assertSkillHasCategoryWithId(LinkedHashMap skill, String expectedCategoryId){
        LinkedHashMap categoryOnSkill = (LinkedHashMap) skill.get(SkillDTO.SkillDTOFields.CATEGORY.key());
        assertEquals(expectedCategoryId, categoryOnSkill.get("id"));
    }

    public static void assertSkillHasGradeWithId(LinkedHashMap skill, String expectedGradeId){
        LinkedHashMap gradeOnSkill = (LinkedHashMap) skill.get(SkillDTO.SkillDTOFields.GRADE.key());
        assertEquals(expectedGradeId, gradeOnSkill.get("id"));
    }
}
