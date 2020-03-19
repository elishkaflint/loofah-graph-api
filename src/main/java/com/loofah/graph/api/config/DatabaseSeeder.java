package com.loofah.graph.api.config;

import com.loofah.graph.api.models.Skill;
import com.loofah.graph.api.repositories.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private SkillsRepository skillsRepository;

    @Autowired
    public DatabaseSeeder(SkillsRepository skillsRepository) {
        this.skillsRepository = skillsRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        skillsRepository.save(Skill.builder().withId("1").withTitle("title1").withDescription( "description1").build());
        skillsRepository.save(Skill.builder().withId("2").withTitle("title2").withDescription( "description2").build());
        skillsRepository.save(Skill.builder().withId("3").withTitle("title3").withDescription( "description3").build());
    }


}
