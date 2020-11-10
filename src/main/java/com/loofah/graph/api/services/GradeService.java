package com.loofah.graph.api.services;

import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.retrievers.DataRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GradeService {

    private DataRetriever dataRetriever;

    @Autowired
    public GradeService(DataRetriever dataRetriever) {
        this.dataRetriever = dataRetriever;
    }

    public Grade getById(String id) {
        return dataRetriever.getGradeById(id);
    }

    public List<Grade> getAll() {
        return dataRetriever.getAllGrades();
    }
}
