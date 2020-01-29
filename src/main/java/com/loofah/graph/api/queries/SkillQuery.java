package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.Skill;
import com.loofah.graph.api.repositories.SkillsRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SkillQuery implements DataFetcher<Skill> {

    private SkillsRepository skillsRepository;

    @Autowired
    public SkillQuery(SkillsRepository skillsRepository) {
        this.skillsRepository = skillsRepository;
    }

    @Override
    public Skill get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = dataFetchingEnvironment.getArgument("id");
        return skillsRepository.findById(id).get();
        // TODO: handle unhappy  path where optional is empty
    }

}
