package com.loofah.graph.api.services;

import com.loofah.graph.api.exceptions.DataNotFoundException;
import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.retrievers.DataRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class CategoryService {

    private final DataRetriever dataRetriever;

    @Autowired
    public CategoryService(final DataRetriever dataRetriever) {
        this.dataRetriever = dataRetriever;
    }

    public Category getById(final String id) {
        return dataRetriever.getCategoryById(id).orElseThrow(() -> new DataNotFoundException("no category found with id [" + id + "]"));
    }

    public Category getByTitle(final String title) {
        return dataRetriever.getCategoryByTitle(title).orElseThrow(() -> new DataNotFoundException("no category found with title [" + title + "]"));
    }

    public List<Category> getAll() {
        return dataRetriever.getAllCategories()
                .stream()
                .sorted()
                .collect(toList());
    }
}
