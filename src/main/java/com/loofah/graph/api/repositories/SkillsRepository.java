package com.loofah.graph.api.repositories;

import com.loofah.graph.api.models.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillsRepository {

    private List<Skill> skills;

    @Autowired
    public SkillsRepository(List<Skill> setUpSkills) {
        this.skills = setUpSkills;
    }

    public List<Skill> getSkills() {
        return this.skills;
    }

    public Skill getSkill(String id) {
        for(Skill skill : this.skills) {
            if(skill.getId().equals(id)) {
                return skill;
            }
        }
        return null;
    }

}
