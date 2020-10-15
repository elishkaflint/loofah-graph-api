package com.loofah.graph.api.integrationTests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loofah.graph.api.GraphAPIApplication;
import com.loofah.graph.api.config.DatabaseSeeder;
import com.loofah.graph.api.models.Request;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.loofah.graph.api.helpers.IntegrationTestConstants.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {
        GraphAPIApplication.class,
        EmbeddedMongoConfig.class
})
public class IntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Value("classpath:testQueries/skillQuery.txt")
    private Resource skillQuery;

    @Value("classpath:testQueries/skillsQuery.txt")
    private Resource skillsQuery;

    @Value("classpath:testQueries/categoryQuery.txt")
    private Resource categoryQuery;

    @Value("classpath:testQueries/categoriesQuery.txt")
    private Resource categoriesQuery;

    @Value("classpath:testQueries/skillsByCategoryQuery.txt")
    private Resource skillsByCategoryQuery;

    @Value("classpath:testQueries/craftsQuery.txt")
    private Resource craftsQuery;

    @Value("classpath:testQueries/craftQuery.txt")
    private Resource craftQuery;

    @Value("classpath:testQueries/gradesQuery.txt")
    private Resource gradesQuery;

    @Value("classpath:testQueries/gradeQuery.txt")
    private Resource gradeQuery;

    private HttpHeaders headers;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void returns_correct_skill_when_id_is_valid() throws IOException {

        final ResponseEntity<String> response = callAPI(skillQuery);

        final JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(SKILL);
        final LinkedHashMap selectedSkill = objectMapper.convertValue(parsedResponseBody, LinkedHashMap.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("description1", selectedSkill.get("description"));
    }

    @Test
    public void returns_all_seeded_skills() throws IOException {

        final ResponseEntity<String> response = callAPI(skillsQuery);

        final JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(SKILLS);
        final List<LinkedHashMap> allSkills = objectMapper.convertValue(parsedResponseBody, ArrayList.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(DatabaseSeeder.SKILLS.size(), allSkills.size());
    }

    @Test
    public void returns_correct_category_when_id_is_valid() throws IOException {

        final ResponseEntity<String> response = callAPI(categoryQuery);

        final JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(CATEGORY);
        final LinkedHashMap selectedSkill = objectMapper.convertValue(parsedResponseBody, LinkedHashMap.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("categoryDescription1", selectedSkill.get("description"));
    }

    @Test
    public void returns_all_seeded_categories() throws IOException {

        final ResponseEntity<String> response = callAPI(categoriesQuery);

        final JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(CATEGORIES);
        final List<LinkedHashMap> allCategoriesResponse = objectMapper.convertValue(parsedResponseBody, ArrayList.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(DatabaseSeeder.CATEGORIES.size(), allCategoriesResponse.size());
    }

    @Test
    public void returns_correct_skills_for_category_id() throws IOException {

        final ResponseEntity<String> response = callAPI(skillsByCategoryQuery);

        final JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(SKILLS_BY_CATEGORY);
        final List<LinkedHashMap> selectedSkillsForCategory = objectMapper.convertValue(parsedResponseBody, ArrayList.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        selectedSkillsForCategory.forEach(skill -> {
            assertEquals("1", skill.get("categoryId"));
        });
    }

    @Test
    public void returns_all_seeded_crafts() throws IOException {

        final ResponseEntity<String> response = callAPI(craftsQuery);

        final JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(CRAFTS);
        final List<LinkedHashMap> allCraftsResponse = objectMapper.convertValue(parsedResponseBody, ArrayList.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(DatabaseSeeder.CRAFTS.size(), allCraftsResponse.size());
    }

    @Test
    public void returns_correct_craft_when_id_is_valid() throws IOException {

        final ResponseEntity<String> response = callAPI(craftQuery);

        final JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(CRAFT);
        final LinkedHashMap selectedCraft = objectMapper.convertValue(parsedResponseBody, LinkedHashMap.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("craftDescription1", selectedCraft.get("description"));
    }

    @Test
    public void returns_all_seeded_grades() throws IOException {
        final ResponseEntity<String> response = callAPI(gradesQuery);

        final JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(GRADES);
        final List<LinkedHashMap> allGrades = objectMapper.convertValue(parsedResponseBody, ArrayList.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(DatabaseSeeder.GRADES.size(), allGrades.size());
    }

    @Test
    public void returns_correct_grade_when_id_valid() throws IOException {
        final ResponseEntity<String> response = callAPI(gradeQuery);

        final JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(GRADE);
        final LinkedHashMap selectedGrade = objectMapper.convertValue(parsedResponseBody, LinkedHashMap.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("gradeDescription1", selectedGrade.get("description"));
    }

    private ResponseEntity<String> callAPI(final Resource query) throws IOException {
        final Request request = getRequest(query);
        final HttpEntity<Object> requestEntity = new HttpEntity<>(request, headers);
        return testRestTemplate.exchange(getURI(), HttpMethod.POST, requestEntity, String.class);
    }

    private Request getRequest(final Resource resource) throws IOException {
        final String query = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        return new Request(query, null);
    }

    private String getURI() {
        return "http://localhost:" + port;
    }
}
