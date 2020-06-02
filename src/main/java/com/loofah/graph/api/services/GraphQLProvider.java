package com.loofah.graph.api.services;

import com.loofah.graph.api.queries.AllCategoriesQuery;
import com.loofah.graph.api.queries.AllSkillsQuery;
import com.loofah.graph.api.queries.SkillQuery;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class GraphQLProvider {

    @Value("classpath:skills.graphqls")
    Resource resource;

    private GraphQL graphQL;

    private AllSkillsQuery allSkillsQuery;
    private SkillQuery skillQuery;
    private AllCategoriesQuery allCategoriesQuery;

    @Autowired
    public GraphQLProvider(AllSkillsQuery allSkillsQuery, SkillQuery skillQuery, AllCategoriesQuery allCategoriesQuery){
        this.allSkillsQuery = allSkillsQuery;
        this.skillQuery = skillQuery;
        this.allCategoriesQuery = allCategoriesQuery;
    }

    @PostConstruct
    private void loadSchema() throws IOException {
        InputStream schemaFile = resource.getInputStream();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(new InputStreamReader(schemaFile));
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        Map<String, DataFetcher> dataFetchers = new HashMap<>();
        dataFetchers.put("allSkills", allSkillsQuery);
        dataFetchers.put("skill", skillQuery);
        dataFetchers.put("allCategories", allCategoriesQuery);

        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring.dataFetchers(dataFetchers))
                .build();
    }

    public ExecutionResult execute(String query) {
        return graphQL.execute(query);
    }

}
