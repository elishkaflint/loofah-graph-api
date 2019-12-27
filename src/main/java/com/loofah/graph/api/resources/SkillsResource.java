package com.loofah.graph.api.resources;

import com.loofah.graph.api.services.SkillsService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/skills")
@RestController
public class SkillsResource {

    private SkillsService graphQLService;

    @Autowired
    public SkillsResource(SkillsService graphQLService) {
        this.graphQLService = graphQLService;
    }

    @GetMapping
    public ResponseEntity<Object> getSkills(@RequestBody String query) {
        ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
        return new ResponseEntity<>(executionResult, HttpStatus.OK);
    }

}
