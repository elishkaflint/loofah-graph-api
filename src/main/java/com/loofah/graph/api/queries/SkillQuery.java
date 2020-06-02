package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.repositories.SkillRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SkillQuery implements DataFetcher<Skill> {

    private SkillRepository skillRepository;

    @Autowired
    public SkillQuery(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = dataFetchingEnvironment.getArgument("id");
        return skillRepository.findById(id).get();
        // TODO: handle unhappy  path where optional is empty
    }

}
