package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.repositories.CategoryRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoriesQuery implements DataFetcher<List<Category>> {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoriesQuery(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return categoryRepository.findAll();
    }

}
