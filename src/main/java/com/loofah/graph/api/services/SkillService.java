package com.loofah.graph.api.services;

import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.models.filters.SkillFilter;
import com.loofah.graph.api.providers.MongoQueryProvider;
import com.loofah.graph.api.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillService {

    private final SkillRepository skillRepository;
    private final MongoQueryProvider mongoQueryProvider;

    @Autowired
    public SkillService(final SkillRepository skillRepository,
                        final MongoQueryProvider mongoQueryProvider) {
        this.skillRepository = skillRepository;
        this.mongoQueryProvider = mongoQueryProvider;
    }

    public Skill getById(String id) {
        return skillRepository.findById(id).get();
    }

    public List<Skill> getWithFilter(SkillFilter skillFilter) {
        Query query = mongoQueryProvider.buildMongoQuery(skillFilter);
        return skillRepository.findWithQuery(query, Skill.class);
    }

}
