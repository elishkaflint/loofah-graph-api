package com.loofah.graph.api.integrationTests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loofah.graph.api.GraphAPIApplication;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.loofah.graph.api.helpers.IntegrationTestConstants.CATEGORIES;
import static com.loofah.graph.api.helpers.IntegrationTestConstants.CATEGORY;
import static com.loofah.graph.api.helpers.IntegrationTestConstants.DATA;
import static com.loofah.graph.api.helpers.IntegrationTestConstants.SKILL;
import static com.loofah.graph.api.helpers.IntegrationTestConstants.SKILLS;
import static com.loofah.graph.api.helpers.IntegrationTestConstants.SKILLS_BY_CATEGORY;
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

    @Value("classpath:testQueries/CategoryQuery.txt")
    private Resource categoryQuery;

    @Value("classpath:testQueries/categoriesQuery.txt")
    private Resource categoriesQuery;

    @Value("classpath:testQueries/skillsByCategoryQuery.txt")
    private Resource skillsByCategoryQuery;

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

        ResponseEntity<String> response = callAPI(skillQuery);

        JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(SKILL);
        LinkedHashMap selectedSkill = objectMapper.convertValue(parsedResponseBody, LinkedHashMap.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("description1", selectedSkill.get("description"));
    }

    @Test
    public void returns_all_seeded_skills() throws IOException {

        ResponseEntity<String> response = callAPI(skillsQuery);

        JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(SKILLS);
        List<LinkedHashMap> allSkills = objectMapper.convertValue(parsedResponseBody, ArrayList.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, allSkills.size());
    }

    @Test
    public void returns_correct_category_when_id_is_valid() throws IOException {

        ResponseEntity<String> response = callAPI(categoryQuery);

        JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(CATEGORY);
        LinkedHashMap selectedSkill = objectMapper.convertValue(parsedResponseBody, LinkedHashMap.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("categoryDescription1", selectedSkill.get("description"));
    }

    @Test
    public void returns_all_seeded_categories() throws IOException {

        ResponseEntity<String> response = callAPI(categoriesQuery);

        JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(CATEGORIES);
        List<LinkedHashMap> allCategoriesResponse = objectMapper.convertValue(parsedResponseBody, ArrayList.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, allCategoriesResponse.size());
    }

    @Test
    public void returns_correct_skills_for_category_id() throws IOException {

        ResponseEntity<String> response = callAPI(skillsByCategoryQuery);

        JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(SKILLS_BY_CATEGORY);
        List<LinkedHashMap> selectedSkillsForCategory = objectMapper.convertValue(parsedResponseBody, ArrayList.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, selectedSkillsForCategory.size());
        assertEquals("1", selectedSkillsForCategory.get(0).get("categoryId"));
        assertEquals("1", selectedSkillsForCategory.get(1).get("categoryId"));
    }

    private ResponseEntity<String> callAPI(Resource query) throws IOException {
        Request request = getRequest(query);
        final HttpEntity<Object> requestEntity = new HttpEntity<>(request, headers);
        return testRestTemplate.exchange(getURI(), HttpMethod.POST, requestEntity, String.class);
    }

    private Request getRequest(Resource resource) throws IOException {
        String query = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        return new Request(query);
    }

    private String getURI() {
        return "http://localhost:" + port;
    }
}
