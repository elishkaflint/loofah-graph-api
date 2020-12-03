package com.loofah.graph.api.services;

import com.loofah.graph.api.exceptions.DataNotFoundException;
import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.retrievers.DataRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CraftService {

    private DataRetriever dataRetriever;

    @Autowired
    public CraftService(DataRetriever dataRetriever) {
        this.dataRetriever = dataRetriever;
    }

    public Craft getById(String id) {
        return dataRetriever.getCraftById(id).orElseThrow(() -> new DataNotFoundException("no craft found with id ["+id+"]"));
    }

    public Craft getByTitle(String title) {
        return dataRetriever.getCraftByTitle(title).orElseThrow(() -> new DataNotFoundException("no craft found with title ["+title+"]"));
    }

    public List<Craft> getAll() {
        return dataRetriever.getAllCrafts();
    }
}
