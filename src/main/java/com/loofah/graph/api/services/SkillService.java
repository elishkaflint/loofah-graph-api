package com.loofah.graph.api.services;

import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.models.filters.SkillFilter;
import com.loofah.graph.api.retrievers.DataRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillService {

    private final DataRetriever dataRetriever;

    @Autowired
    public SkillService(DataRetriever dataRetriever) {
        this.dataRetriever = dataRetriever;
    }

    public Skill getById(String id) {
        return dataRetriever.getSkillById(id);
    }

    public List<Skill> getWithFilter(SkillFilter skillFilter) {
        return dataRetriever.getSkillWithFilter(skillFilter);
    }

}
