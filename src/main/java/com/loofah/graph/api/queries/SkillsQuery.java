package com.loofah.graph.api.queries;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.models.filters.SkillFilter;
import com.loofah.graph.api.repositories.SkillRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SkillsQuery implements DataFetcher<List<Skill>> {

    private final SkillRepository skillRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public SkillsQuery(final SkillRepository skillRepository, final ObjectMapper objectMapper) {
        this.skillRepository = skillRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Skill> get(final DataFetchingEnvironment dataFetchingEnvironment) {

        Query query = new Query();

        SkillFilter skillFilter = objectMapper.convertValue(dataFetchingEnvironment.getArgument("filter"), SkillFilter.class);
        Optional<SkillFilter> optionalSkillFilter = Optional.ofNullable(skillFilter);

        optionalSkillFilter.map(SkillFilter::getCategoryId).ifPresent(
            categoryId -> query.addCriteria(Criteria.where("categoryId").is(categoryId)
        ));

        optionalSkillFilter.map(SkillFilter::getGradeId).ifPresent(
            gradeId -> query.addCriteria(Criteria.where("gradeId").is(gradeId)
        ));

        return skillRepository.findWithQuery(query, Skill.class);

    }

}
