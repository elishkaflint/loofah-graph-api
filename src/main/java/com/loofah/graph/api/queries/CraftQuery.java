package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.repositories.CraftRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CraftQuery implements DataFetcher<Craft> {

    private final CraftRepository craftRepository;

    @Autowired
    public CraftQuery(final CraftRepository craftRepository) {
        this.craftRepository = craftRepository;
    }

    @Override
    public Craft get(DataFetchingEnvironment dataFetchingEnvironment) {
        final String id = dataFetchingEnvironment.getArgument("id");
        return craftRepository.findById(id).get();
        // TODO: handle unhappy  path where optional is empty
    }

}
