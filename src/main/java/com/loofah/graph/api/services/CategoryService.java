package com.loofah.graph.api.services;

import com.loofah.graph.api.exceptions.DataNotFoundException;
import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.retrievers.DataRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryService {

    private DataRetriever dataRetriever;

    @Autowired
    public CategoryService(DataRetriever dataRetriever) {
        this.dataRetriever = dataRetriever;
    }

    public Category getById(String id) {
        return dataRetriever.getCategoryById(id).orElseThrow(() -> new DataNotFoundException("no category found with id ["+id+"]"));
    }

    public List<Category> getAll() {
        return dataRetriever.getAllCategories();
    }
}
