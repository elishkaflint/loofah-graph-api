package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.repositories.CategoryRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryQuery implements DataFetcher<Category> {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryQuery(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category get(final DataFetchingEnvironment dataFetchingEnvironment) {
        final String id = dataFetchingEnvironment.getArgument("id");
        return categoryRepository.findById(id).get();
        // TODO: handle unhappy  path where optional is empty
    }
}
