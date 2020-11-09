package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.services.CraftService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CraftsQuery implements DataFetcher<List<Craft>> {

    private final CraftService craftService;

    @Autowired
    public CraftsQuery(final CraftService craftService) {
        this.craftService = craftService;
    }

    @Override
    public List<Craft> get(final DataFetchingEnvironment dataFetchingEnvironment) {
        return craftService.getAll();
    }
}
