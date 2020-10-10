package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.repositories.GradeRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GradeQuery implements DataFetcher<Grade> {

    private GradeRepository gradeRepository;

    @Autowired
    public GradeQuery(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public Grade get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = dataFetchingEnvironment.getArgument("id");
        return gradeRepository.findById(id).get();
        // TODO: handle unhappy  path where optional is empty
    }

}
