package com.loofah.graph.api.integrationTests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loofah.graph.api.GraphAPIApplication;
import com.loofah.graph.api.models.DTO.SkillDTO;
import com.loofah.graph.api.models.Request;
import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.models.database.Grade;
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
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static com.loofah.graph.api.helpers.IntegrationTestConstants.CATEGORIES;
import static com.loofah.graph.api.helpers.IntegrationTestConstants.CATEGORY;
import static com.loofah.graph.api.helpers.IntegrationTestConstants.CRAFT;
import static com.loofah.graph.api.helpers.IntegrationTestConstants.CRAFTS;
import static com.loofah.graph.api.helpers.IntegrationTestConstants.DATA;
import static com.loofah.graph.api.helpers.IntegrationTestConstants.GRADE;
import static com.loofah.graph.api.helpers.IntegrationTestConstants.GRADES;
import static com.loofah.graph.api.helpers.IntegrationTestConstants.SKILL;
import static com.loofah.graph.api.helpers.IntegrationTestConstants.SKILLS;
import static com.loofah.graph.api.helpers.IntegrationTestHelpers.assertResponseHasErrorMessage;
import static com.loofah.graph.api.helpers.IntegrationTestHelpers.assertResponseHasNullData;
import static com.loofah.graph.api.helpers.IntegrationTestHelpers.assertSkillHasCategoryWithId;
import static com.loofah.graph.api.models.DTO.SkillDTO.SkillDTOFields.CRAFT_IDS;
import static com.loofah.graph.api.models.DTO.SkillDTO.SkillDTOFields.GRADE_ID;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.in;
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

    @Autowired
    private DatabaseSeeder databaseSeeder;

    @Value("classpath:testQueries/skillQuery.txt")
    private Resource skillQuery;

    @Value("classpath:testQueries/skillQueryIdNotFound.txt")
    private Resource skillQueryIdNotFound;

    @Value("classpath:testQueries/skillsQueryNoFilter.txt")
    private Resource skillsQueryNoFilter;

    @Value("classpath:testQueries/skillsQueryCategoryFilter.txt")
    private Resource skillsQueryCategoryFilter;

    @Value("classpath:testQueries/skillsQueryGradeFilter.txt")
    private Resource skillsQueryGradeFilter;

    @Value("classpath:testQueries/skillsQueryCraftFilter.txt")
    private Resource skillsQueryCraftFilter;

    @Value("classpath:testQueries/skillsQueryAllFilters.txt")
    private Resource skillsQueryAllFilters;

    @Value("classpath:testQueries/categoryQuery.txt")
    private Resource categoryQuery;

    @Value("classpath:testQueries/categoryQueryIdNotFound.txt")
    private Resource categoryQueryIdNotFound;

    @Value("classpath:testQueries/categoriesQuery.txt")
    private Resource categoriesQuery;

    @Value("classpath:testQueries/craftsQuery.txt")
    private Resource craftsQuery;

    @Value("classpath:testQueries/craftQuery.txt")
    private Resource craftQuery;

    @Value("classpath:testQueries/craftQueryIdNotFound.txt")
    private Resource craftQueryIdNotFound;

    @Value("classpath:testQueries/gradesQuery.txt")
    private Resource gradesQuery;

    @Value("classpath:testQueries/gradeQuery.txt")
    private Resource gradeQuery;

    @Value("classpath:testQueries/gradeQueryIdNotFound.txt")
    private Resource gradeQueryIdNotFound;

    private HttpHeaders headers;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        objectMapper = new ObjectMapper();
        databaseSeeder.setUp();
    }

    @Test
    public void returns_correct_skill_when_id_is_valid() throws IOException {

        final ResponseEntity<String> response = callAPI(skillQuery);

        assertNotNull("response body should not be null", response.getBody());
        final JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(SKILL);
        final LinkedHashMap selectedSkill = objectMapper.convertValue(parsedResponseBody, LinkedHashMap.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("description1", selectedSkill.get(SkillDTO.SkillDTOFields.DESCRIPTION.key()));
    }

    @Test
    public void returns_200_with_error_in_response_when_skill_is_not_found_for_given_id() throws IOException {

        final ResponseEntity<String> response = callAPI(skillQueryIdNotFound);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertResponseHasNullData(objectMapper, response);
        assertResponseHasErrorMessage(objectMapper, response, "Exception while fetching data (/skill) : No skill found with id [0]");
    }

    @Test
    public void returns_all_seeded_skills() throws IOException {

        final ResponseEntity<String> response = callAPI(skillsQueryNoFilter);

        assertNotNull("response body should not be null", response.getBody());
        final JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(SKILLS);
        final List<LinkedHashMap> allSkills = objectMapper.convertValue(parsedResponseBody, ArrayList.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(DatabaseSeeder.SKILLS.size(), allSkills.size());
    }

    @Test
    public void returns_correct_skills_for_category_id() throws IOException {

        final ResponseEntity<String> response = callAPI(skillsQueryCategoryFilter);

        assertNotNull("response body should not be null", response.getBody());
        final JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(SKILLS);
        final List<LinkedHashMap> selectedSkillsForCategory = objectMapper.convertValue(parsedResponseBody, ArrayList.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        selectedSkillsForCategory.forEach(skill -> {
            assertSkillHasCategoryWithId(skill, "1");
        });
    }

    @Test
    public void returns_correct_skills_for_grade_id() throws IOException {

        final ResponseEntity<String> response = callAPI(skillsQueryGradeFilter);

        assertNotNull("response body should not be null", response.getBody());
        final JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(SKILLS);
        final List<LinkedHashMap> selectedSkillsForGrade = objectMapper.convertValue(parsedResponseBody, ArrayList.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        selectedSkillsForGrade.forEach(skill -> {
            assertEquals("2", skill.get(GRADE_ID.key()));
        });
    }

    @Test
    public void returns_correct_skills_for_craft_ids() throws IOException {

        final ResponseEntity<String> response = callAPI(skillsQueryCraftFilter);

        assertNotNull("response body should not be null", response.getBody());
        final JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(SKILLS);
        final List<LinkedHashMap> selectedSkillsForCraft = objectMapper.convertValue(parsedResponseBody, ArrayList.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        selectedSkillsForCraft.forEach(skill -> {
            assertThat((List<String>) skill.get(CRAFT_IDS.key()), hasItem(in(Arrays.asList("1", "2"))));
        });
    }

    @Test
    public void returns_correct_skills_for_grade_id_and_category_id_and_craft_ids() throws IOException {

        final ResponseEntity<String> response = callAPI(skillsQueryAllFilters);

        assertNotNull("response body should not be null", response.getBody());
        final JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(SKILLS);
        final List<LinkedHashMap> selectedSkillsForCategoryAndGrade = objectMapper.convertValue(parsedResponseBody, ArrayList.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        selectedSkillsForCategoryAndGrade.forEach(skill -> {
            assertSkillHasCategoryWithId(skill, "2");
            assertEquals("2", skill.get(GRADE_ID.key()));
            assertThat((List<String>) skill.get(CRAFT_IDS.key()), hasItem(in(Arrays.asList("2", "3"))));
        });
    }



    @Test
    public void returns_correct_category_when_id_is_valid() throws IOException {

        final ResponseEntity<String> response = callAPI(categoryQuery);

        assertNotNull("response body should not be null", response.getBody());
        final JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(CATEGORY);
        final LinkedHashMap selectedCategory = objectMapper.convertValue(parsedResponseBody, LinkedHashMap.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("categoryDescription1", selectedCategory.get(Category.CategoryFields.DESCRIPTION.key()));
    }

    @Test
    public void returns_200_with_error_in_response_when_category_is_not_found_for_given_id() throws IOException {

        final ResponseEntity<String> response = callAPI(categoryQueryIdNotFound);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertResponseHasNullData(objectMapper, response);
        assertResponseHasErrorMessage(objectMapper, response, "Exception while fetching data (/category) : No Category found with id [0]");

    }

    @Test
    public void returns_all_seeded_categories() throws IOException {

        final ResponseEntity<String> response = callAPI(categoriesQuery);

        assertNotNull("response body should not be null", response.getBody());
        final JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(CATEGORIES);
        final List<LinkedHashMap> allCategoriesResponse = objectMapper.convertValue(parsedResponseBody, ArrayList.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(DatabaseSeeder.CATEGORIES.size(), allCategoriesResponse.size());
    }

    @Test
    public void returns_all_seeded_crafts() throws IOException {

        final ResponseEntity<String> response = callAPI(craftsQuery);

        assertNotNull("response body should not be null", response.getBody());
        final JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(CRAFTS);
        final List<LinkedHashMap> allCraftsResponse = objectMapper.convertValue(parsedResponseBody, ArrayList.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(DatabaseSeeder.CRAFTS.size(), allCraftsResponse.size());
    }

    @Test
    public void returns_correct_craft_when_id_is_valid() throws IOException {

        final ResponseEntity<String> response = callAPI(craftQuery);

        assertNotNull("response body should not be null", response.getBody());
        final JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(CRAFT);
        final LinkedHashMap selectedCraft = objectMapper.convertValue(parsedResponseBody, LinkedHashMap.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("craftDescription1", selectedCraft.get(Craft.CraftFields.DESCRIPTION.key()));
    }

    @Test
    public void returns_200_with_error_in_response_when_craft_is_not_found_for_given_id() throws IOException {

        final ResponseEntity<String> response = callAPI(craftQueryIdNotFound);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertResponseHasNullData(objectMapper, response);
        assertResponseHasErrorMessage(objectMapper, response, "Exception while fetching data (/craft) : No Craft found with id [0]");

    }

    @Test
    public void returns_all_seeded_grades() throws IOException {
        final ResponseEntity<String> response = callAPI(gradesQuery);

        assertNotNull("response body should not be null", response.getBody());
        final JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(GRADES);
        final List<LinkedHashMap> allGrades = objectMapper.convertValue(parsedResponseBody, ArrayList.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(DatabaseSeeder.GRADES.size(), allGrades.size());
    }

    @Test
    public void returns_correct_grade_when_id_valid() throws IOException {
        final ResponseEntity<String> response = callAPI(gradeQuery);

        assertNotNull("response body should not be null", response.getBody());
        final JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get(DATA).get(GRADE);
        final LinkedHashMap selectedGrade = objectMapper.convertValue(parsedResponseBody, LinkedHashMap.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("gradeDescription1", selectedGrade.get(Grade.GradeFields.DESCRIPTION.key()));
    }

    @Test
    public void returns_200_with_error_in_response_when_grade_is_not_found_for_given_id() throws IOException {

        final ResponseEntity<String> response = callAPI(gradeQueryIdNotFound);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertResponseHasNullData(objectMapper, response);
        assertResponseHasErrorMessage(objectMapper, response, "Exception while fetching data (/grade) : No Grade found with id [0]");

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
