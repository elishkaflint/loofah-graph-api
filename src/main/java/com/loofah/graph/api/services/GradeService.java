package com.loofah.graph.api.services;

import com.loofah.graph.api.exceptions.DataNotFoundException;
import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.retrievers.DataRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class GradeService {

    private final DataRetriever dataRetriever;

    @Autowired
    public GradeService(final DataRetriever dataRetriever) {
        this.dataRetriever = dataRetriever;
    }

    public Grade getById(final String id) {
        return dataRetriever.getGradeById(id).orElseThrow(() -> new DataNotFoundException("no grade found with id [" + id + "]"));
    }

    public Grade getByTitle(final String title) {
        return dataRetriever.getGradeByTitle(title).orElseThrow(() -> new DataNotFoundException("no grade found with title [" + title + "]"));
    }

    public List<Grade> getAll() {
        return dataRetriever.getAllGrades()
                .stream()
                .sorted()
                .collect(toList());
    }
}
