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

        optionalFilter.map(SkillFilter::getCategoryTitle).ifPresent(
                categoryTitle -> query.addCriteria(Criteria.where("categoryTitle").is(categoryTitle))
        );

        optionalFilter.map(SkillFilter::getGradeTitle).ifPresent(
                gradeTitle -> query.addCriteria(Criteria.where("gradeTitle").is(gradeTitle))
        );

        optionalFilter.map(SkillFilter::getCraftTitles).ifPresent(
                craftTitles -> query.addCriteria(Criteria.where("craftTitles").in(craftTitles))
        );

        return query;
    }

}
