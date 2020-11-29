package com.loofah.graph.api.queries;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loofah.graph.api.models.dto.GroupedSkillDTO;
import com.loofah.graph.api.models.filters.SkillFilter;
import com.loofah.graph.api.services.SkillService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupedSkillsQuery implements DataFetcher<List<GroupedSkillDTO>> {

    private final SkillService skillService;
    private final ObjectMapper objectMapper;

    public GroupedSkillsQuery(SkillService skillService, ObjectMapper objectMapper) {
        this.skillService = skillService;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<GroupedSkillDTO> get(DataFetchingEnvironment dataFetchingEnvironment) {
        SkillFilter skillFilter = objectMapper.convertValue(dataFetchingEnvironment.getArgument("filter"), SkillFilter.class);
        return skillService.getGroupedWithFilter(skillFilter);
    }

}
