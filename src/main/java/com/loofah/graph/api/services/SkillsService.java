package com.loofah.graph.api.services;

import com.loofah.graph.api.queries.AllSkillsQuery;
import com.loofah.graph.api.queries.SkillQuery;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
public class SkillsService {

    @Value("classpath:skills.graphqls")
    Resource resource;

    private GraphQL graphQL;

    private AllSkillsQuery allSkillsQuery;
    private SkillQuery skillQuery;

    @Autowired
    public SkillsService(AllSkillsQuery allSkillsQuery, SkillQuery skillQuery){
        this.allSkillsQuery = allSkillsQuery;
        this.skillQuery = skillQuery;
    }

    @PostConstruct
    private void loadSchema() throws IOException {
        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                .dataFetcher("allSkills", allSkillsQuery)
                .dataFetcher("skill", skillQuery))
                .build();
    }

    public ExecutionResult execute(String query) {
        return graphQL.execute(query);
    }

}
