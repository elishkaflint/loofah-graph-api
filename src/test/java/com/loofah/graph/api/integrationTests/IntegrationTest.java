package com.loofah.graph.api.integrationTests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loofah.graph.api.testConfig.GraphAPITestConfiguration;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = GraphAPITestConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
        headers.setContentType(MediaType.TEXT_PLAIN);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void returns_all_skills() throws IOException {

        String query = getPayload(allSkillsQuery);

        final HttpEntity<Object> requestEntity = new HttpEntity<>(query, headers);

        ResponseEntity<String> response = testRestTemplate.exchange(getURI(), HttpMethod.POST, requestEntity, String.class);

        JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get("data").get("allSkills");
        List<LinkedHashMap> string = objectMapper.convertValue(parsedResponseBody, ArrayList.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, string.size());
    }

    @Test
    public void returns_correct_skills_when_id_is_valid() throws IOException {

        String query = getPayload(skillQueryValidID);

        final HttpEntity<Object> requestEntity = new HttpEntity<>(query, headers);

        ResponseEntity<String> response = testRestTemplate.exchange(getURI(), HttpMethod.POST, requestEntity, String.class);

        JsonNode parsedResponseBody = objectMapper.readTree(response.getBody()).get("data").get("skill");
        LinkedHashMap string = objectMapper.convertValue(parsedResponseBody, LinkedHashMap.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, string.size());
        assertEquals("testDescription1", string.get("description"));
    }

    private String getURI() {
        return "http://localhost:" + port + "/skills";
    }

    private String getPayload(Resource resource) throws IOException {
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }
}
