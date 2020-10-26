package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.services.CraftService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CraftQuery implements DataFetcher<Craft> {

    private final CraftService craftService;

    @Autowired
    public CraftQuery(final CraftService craftService) {
        this.craftService = craftService;
    }

    @Override
    public Craft get(final DataFetchingEnvironment dataFetchingEnvironment) {
        final String id = dataFetchingEnvironment.getArgument("id");
        return craftService.getById(id);
        // TODO: handle unhappy  path where optional is empty
    }

}
