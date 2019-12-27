package com.loofah.graph.api.testConfig;

import com.loofah.graph.api.models.Skill;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

@TestConfiguration
public class GraphAPITestConfiguration {

    @Bean
    @Primary
    public List<Skill> setUpTestSkills() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("1", "testTitle1", "testDescription1"));
        skills.add(new Skill("2", "testTitle2", "testDescription2"));
        skills.add(new Skill("3", "testTitle3", "testDescription3"));
        return skills;
    }

}
