package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.repositories.GradeRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GradesQuery implements DataFetcher<List<Grade>> {

    private GradeRepository gradeRepository;

    @Autowired
    public GradesQuery(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Grade> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return gradeRepository.findAll();
    }

}
