package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.services.GradeService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GradeQuery implements DataFetcher<Grade> {

    private GradeService gradeService;

    @Autowired
    public GradeQuery(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @Override
    public Grade get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = dataFetchingEnvironment.getArgument("id");
        return gradeService.getById(id);
    }

}
