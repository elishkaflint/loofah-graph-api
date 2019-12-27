package com.loofah.graph.api.config;

import com.loofah.graph.api.models.Skill;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class GraphAPIConfiguration {

    @Bean
    public List<Skill> setUpSkills() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("1", "title1", "description1"));
        skills.add(new Skill("2", "title2", "description2"));
        skills.add(new Skill("3", "title3", "description3"));
        return skills;
    }

}
