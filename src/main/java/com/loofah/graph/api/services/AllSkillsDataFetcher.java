package com.loofah.graph.api.services;

import com.loofah.graph.api.models.Skill;
import com.loofah.graph.api.repositories.SkillsRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllSkillsDataFetcher implements DataFetcher<List<Skill>> {

    private SkillsRepository skillsRepository;

    @Autowired
    public AllSkillsDataFetcher(SkillsRepository skillsRepository) {
        this.skillsRepository = skillsRepository;
    }

    @Override
    public List<Skill> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return skillsRepository.getAllSkills();
    }

}
