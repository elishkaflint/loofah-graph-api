package com.loofah.graph.api.providers;

import com.loofah.graph.api.models.filters.SkillFilter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MongoQueryProvider {

    public Query buildMongoQuery(SkillFilter skillFilter) {

        Optional<SkillFilter> optionalFilter = Optional.ofNullable(skillFilter);

        Query query = new Query();

        optionalFilter.map(SkillFilter::getCategoryId).ifPresent(
                categoryId -> query.addCriteria(Criteria.where("categoryId").is(categoryId))
        );

        optionalFilter.map(SkillFilter::getGradeId).ifPresent(
                gradeId -> query.addCriteria(Criteria.where("gradeId").is(gradeId))
        );

        optionalFilter.map(SkillFilter::getCraftIds).ifPresent(
                craftIds -> query.addCriteria(Criteria.where("craftIds").in(craftIds))
        );

        return query;
    }

}
