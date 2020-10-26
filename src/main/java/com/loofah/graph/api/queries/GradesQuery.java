package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.services.GradeService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GradesQuery implements DataFetcher<List<Grade>> {

    private GradeService gradeService;

    @Autowired
    public GradesQuery(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @Override
    public List<Grade> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return gradeService.getAll();
    }

}
