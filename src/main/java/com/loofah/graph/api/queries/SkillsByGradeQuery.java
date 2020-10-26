package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.repositories.SkillRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Deprecated
public class SkillsByGradeQuery implements DataFetcher<List<Skill>> {

    private final SkillRepository skillRepository;

    @Autowired
    public SkillsByGradeQuery(final SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<Skill> get(final DataFetchingEnvironment dataFetchingEnvironment) {

        final String gradeId = dataFetchingEnvironment.getArgument("gradeId");

        return skillRepository.findByGradeId(gradeId);

    }

}
