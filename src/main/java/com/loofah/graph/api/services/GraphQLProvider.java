package com.loofah.graph.api.services;

import com.loofah.graph.api.queries.CategoriesQuery;
import com.loofah.graph.api.queries.CategoryQuery;
import com.loofah.graph.api.queries.SkillQuery;
import com.loofah.graph.api.queries.SkillsByCategoryQuery;
import com.loofah.graph.api.queries.SkillsQuery;
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

    private SkillsQuery skillsQuery;
    private SkillQuery skillQuery;
    private CategoryQuery categoryQuery;
    private CategoriesQuery categoriesQuery;
    private SkillsByCategoryQuery skillsByCategoryQuery;

    @Autowired
    public GraphQLProvider(SkillsQuery skillsQuery,
                           SkillQuery skillQuery,
                           CategoryQuery categoryQuery,
                           CategoriesQuery categoriesQuery,
                           SkillsByCategoryQuery skillsByCategoryQuery){
        this.skillsQuery = skillsQuery;
        this.skillQuery = skillQuery;
        this.categoryQuery = categoryQuery;
        this.categoriesQuery = categoriesQuery;
        this.skillsByCategoryQuery = skillsByCategoryQuery;
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
        dataFetchers.put("skill", skillQuery);
        dataFetchers.put("skills", skillsQuery);
        dataFetchers.put("skillsByCategory", skillsByCategoryQuery);
        dataFetchers.put("category", categoryQuery);
        dataFetchers.put("categories", categoriesQuery);

        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring.dataFetchers(dataFetchers))
                .build();
    }

    public ExecutionResult execute(String query) {
        return graphQL.execute(query);
    }

}
