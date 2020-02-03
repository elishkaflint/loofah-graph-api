package com.loofah.graph.api.controllers;

import com.loofah.graph.api.services.SkillsService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/skills")
@RestController
public class SkillsController {

    private SkillsService graphQLService;

    @Autowired
    public SkillsController(SkillsService graphQLService) {
        this.graphQLService = graphQLService;
    }

    @PostMapping
    public ResponseEntity<Object> getSkills(@RequestBody String query) {
        ExecutionResult executionResult = graphQLService.execute(query);
        return new ResponseEntity<>(executionResult, HttpStatus.OK);
    }

}
