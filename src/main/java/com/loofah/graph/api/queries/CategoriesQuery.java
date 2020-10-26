package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.services.CategoryService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoriesQuery implements DataFetcher<List<Category>> {

    private final CategoryService categoryService;

    @Autowired
    public CategoriesQuery(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public List<Category> get(final DataFetchingEnvironment dataFetchingEnvironment) {
        return categoryService.getAll();
    }

}
