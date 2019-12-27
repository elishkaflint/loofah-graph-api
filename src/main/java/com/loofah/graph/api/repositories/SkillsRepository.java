package com.loofah.graph.api.repositories;

import com.loofah.graph.api.models.Skill;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SkillsRepository {

    public List<Skill> getAllSkills() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("1", "title1", "description1"));
        skills.add(new Skill("2", "title2", "description2"));
        skills.add(new Skill("3", "title3", "description3"));
        return skills;
    }
}
