package com.loofah.graph.api.services;

import com.loofah.graph.api.queries.*;
import graphql.ExecutionInput;
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

    private final SkillsQuery skillsQuery;
    private final SkillQuery skillQuery;
    private final CategoryQuery categoryQuery;
    private final CategoriesQuery categoriesQuery;
    private final SkillsByCategoryQuery skillsByCategoryQuery;
    private final CraftsQuery craftsQuery;
    private final CraftQuery craftQuery;
    @Value("classpath:skills.graphqls")
    Resource resource;
    private GraphQL graphQL;

    @Autowired
    public GraphQLProvider(final SkillsQuery skillsQuery,
                           final SkillQuery skillQuery,
                           final CategoryQuery categoryQuery,
                           final CategoriesQuery categoriesQuery,
                           final SkillsByCategoryQuery skillsByCategoryQuery,
                           final CraftsQuery craftsQuery,
                           final CraftQuery craftQuery) {
        this.skillsQuery = skillsQuery;
        this.skillQuery = skillQuery;
        this.categoryQuery = categoryQuery;
        this.categoriesQuery = categoriesQuery;
        this.skillsByCategoryQuery = skillsByCategoryQuery;
        this.craftsQuery = craftsQuery;
        this.craftQuery = craftQuery;
    }

    @PostConstruct
    private void loadSchema() throws IOException {
        final InputStream schemaFile = resource.getInputStream();
        final TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(new InputStreamReader(schemaFile));
        final RuntimeWiring wiring = buildRuntimeWiring();
        final GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        final Map<String, DataFetcher> dataFetchers = new HashMap<>();
        dataFetchers.put("skill", skillQuery);
        dataFetchers.put("skills", skillsQuery);
        dataFetchers.put("skillsByCategory", skillsByCategoryQuery);
        dataFetchers.put("category", categoryQuery);
        dataFetchers.put("categories", categoriesQuery);
        dataFetchers.put("crafts", craftsQuery);
        dataFetchers.put("craft", craftQuery);

        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring.dataFetchers(dataFetchers))
                .build();
    }

    public ExecutionResult execute(final String query, Map<String, Object> variables) {
        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .query(query)
                .variables(variables)
                .build();
        return graphQL.execute(executionInput);
    }

}
