package com.loofah.graph.api.queries;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.models.filters.SkillFilter;
import com.loofah.graph.api.services.SkillService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillsQuery implements DataFetcher<List<Skill>> {

    private final SkillService skillService;
    private final ObjectMapper objectMapper;

    @Autowired
    public SkillsQuery(final SkillService skillService,
                       final ObjectMapper objectMapper) {
        this.skillService = skillService;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Skill> get(final DataFetchingEnvironment dataFetchingEnvironment) {
        SkillFilter skillFilter = objectMapper.convertValue(dataFetchingEnvironment.getArgument("filter"), SkillFilter.class);
        return skillService.getWithFilter(skillFilter);
    }

}
