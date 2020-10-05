package com.loofah.graph.api.config;

import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.repositories.CategoryRepository;
import com.loofah.graph.api.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private SkillRepository skillRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public DatabaseSeeder(SkillRepository skillRepository, CategoryRepository categoryRepository) {
        this.skillRepository = skillRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {

        skillRepository.save(Skill.builder().withId("1").withTitle("title1").withDescription( "description1").withCategoryId("1").withExamples(Collections.EMPTY_LIST).build());
        skillRepository.save(Skill.builder().withId("2").withTitle("title2").withDescription( "description2").withCategoryId("1").withExamples(Collections.EMPTY_LIST).build());
        skillRepository.save(Skill.builder().withId("3").withTitle("title3").withDescription( "description3").withCategoryId("2").withExamples(Collections.EMPTY_LIST).build());

        categoryRepository.save(Category.builder().withId("1").withTitle("categoryTitle1").withDescription("categoryDescription1").build());
        categoryRepository.save(Category.builder().withId("2").withTitle("categoryTitle2").withDescription("categoryDescription1").build());
    }

}
