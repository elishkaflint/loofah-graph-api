package com.loofah.graph.api.controllers;

import com.loofah.graph.api.models.SkillsRequest;
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

@CrossOrigin(origins = "*")
@RequestMapping("/skills")
@RestController
public class SkillsController {

    private SkillsService graphQLService;

    @Autowired
    public SkillsController(SkillsService graphQLService) {
        this.graphQLService = graphQLService;
    }

    @PostMapping
    public ResponseEntity<Object> getSkills(@RequestBody SkillsRequest skillsRequest) {
        System.out.println(skillsRequest);
        ExecutionResult executionResult = graphQLService.execute(skillsRequest.getQuery());
        return new ResponseEntity<>(executionResult, HttpStatus.OK);
    }

}
