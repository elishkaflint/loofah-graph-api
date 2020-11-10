package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.services.CategoryService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryQuery implements DataFetcher<Category> {

    private final CategoryService categoryService;

    @Autowired
    public CategoryQuery(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Category get(final DataFetchingEnvironment dataFetchingEnvironment) {
        final String id = dataFetchingEnvironment.getArgument("id");
        return categoryService.getById(id);
    }
}
