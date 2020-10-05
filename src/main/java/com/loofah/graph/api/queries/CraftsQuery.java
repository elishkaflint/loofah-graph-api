package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.repositories.CraftRepository;
import com.loofah.graph.api.repositories.CraftRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CraftsQuery implements DataFetcher<List<Craft>> {

    private CraftRepository craftRepository;

    @Autowired
    public CraftsQuery(CraftRepository craftRepository) {
        this.craftRepository = craftRepository;
    }

    @Override
    public List<Craft> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return craftRepository.findAll();
    }
}
