package com.loofah.graph.api.integrationTests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loofah.graph.api.GraphAPIApplication;
import com.loofah.graph.api.models.SkillRequest;
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

    @Value("classpath:testQueries/allSkillsQuery.txt")
    private Resource allSkillsQuery;

    @Value("classpath:testQueries/skillQueryValidID.txt")
    private Resource skillQueryValidID;

    private HttpHeaders headers;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void returns_all_seeded_skills() throws IOException {

        SkillRequest request = getRequest(allSkillsQuery);

        final HttpEntity<Object> requestEntity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = testRestTemplate.exchange(getURI(), HttpMethod.POST, requestEntity, String.class);

        JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get("data").get("allSkills");
        List<LinkedHashMap> allSkills = objectMapper.convertValue(parsedResponseBody, ArrayList.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, allSkills.size());
    }

    @Test
    public void returns_correct_skill_when_id_is_valid() throws IOException {

        SkillRequest request = getRequest(skillQueryValidID);

        final HttpEntity<Object> requestEntity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = testRestTemplate.exchange(getURI(), HttpMethod.POST, requestEntity, String.class);

        JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get("data").get("skill");
        LinkedHashMap selectedSkill = objectMapper.convertValue(parsedResponseBody, LinkedHashMap.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("description1", selectedSkill.get("description"));
    }

    private SkillRequest getRequest(Resource resource) throws IOException {
        String query = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        return new SkillRequest(query);
    }

    private String getURI() {
        return "http://localhost:" + port + "/skills";
    }
}
