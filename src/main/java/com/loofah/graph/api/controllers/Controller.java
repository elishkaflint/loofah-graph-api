package com.loofah.graph.api.controllers;

import com.loofah.graph.api.models.Request;
import com.loofah.graph.api.services.GraphQLProvider;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class Controller {

    private final GraphQLProvider graphQLProvider;

    @Autowired
    public Controller(final GraphQLProvider graphQLProvider) {
        this.graphQLProvider = graphQLProvider;
    }

    @PostMapping
    public ResponseEntity<Object> getSkills(@RequestBody final Request request) {
        final ExecutionResult executionResult = graphQLProvider.execute(request.getQuery(), request.getVariables());

        return new ResponseEntity<>(executionResult, HttpStatus.OK);
    }

}
