package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.services.SkillService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SkillQuery implements DataFetcher<Skill> {

    private final SkillService skillService;

    @Autowired
    public SkillQuery(final SkillService skillService) {
        this.skillService = skillService;
    }

    @Override
    public Skill get(final DataFetchingEnvironment dataFetchingEnvironment) {
        final String id = dataFetchingEnvironment.getArgument("id");
        return skillService.getById(id);
        // TODO: handle unhappy  path where optional is empty
    }

}
