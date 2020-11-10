package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.DTO.SkillDTO;
import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.services.SkillService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SkillQuery implements DataFetcher<SkillDTO> {

    private final SkillService skillService;

    @Autowired
    public SkillQuery(final SkillService skillService) {
        this.skillService = skillService;
    }

    @Override
    public SkillDTO get(final DataFetchingEnvironment dataFetchingEnvironment) {
        final String id = dataFetchingEnvironment.getArgument("id");
        return skillService.getById(id);
    }

}
