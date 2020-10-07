package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.repositories.SkillRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillsByCategoryQuery implements DataFetcher<List<Skill>> {

    private final SkillRepository skillRepository;

    @Autowired
    public SkillsByCategoryQuery(final SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<Skill> get(final DataFetchingEnvironment dataFetchingEnvironment) {

        final String categoryId = dataFetchingEnvironment.getArgument("categoryId");

        return skillRepository.findByCategoryId(categoryId);

    }

}
