package com.loofah.graph.api.config;

import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.repositories.CategoryRepository;
import com.loofah.graph.api.repositories.CraftRepository;
import com.loofah.graph.api.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    public static final List<Craft> CRAFTS = Arrays.asList(
            Craft.builder().withId("1").withTitle("craftTitle1").withDescription("craftDescription1").withLead(Collections.singletonList("lead1")).withDevServicesPage("page1").withSlackChannels(Collections.emptyList()).build(),
            Craft.builder().withId("2").withTitle("craftTitle2").withDescription("craftDescription2").withLead(Collections.singletonList("lead2")).withDevServicesPage("page2").withSlackChannels(Collections.emptyList()).build()
    );
    public static List<Skill> SKILLS = Arrays.asList(
            Skill.builder().withId("1").withTitle("title1").withDescription( "description1").withCategoryId("1").withExamples(Collections.EMPTY_LIST).build(),
            Skill.builder().withId("2").withTitle("title2").withDescription( "description2").withCategoryId("1").withExamples(Collections.EMPTY_LIST).build(),
            Skill.builder().withId("3").withTitle("title3").withDescription( "description3").withCategoryId("2").withExamples(Collections.EMPTY_LIST).build()
    );

    public static List<Category> CATEGORIES = Arrays.asList(
            Category.builder().withId("1").withTitle("categoryTitle1").withDescription("categoryDescription1").build(),
            Category.builder().withId("2").withTitle("categoryTitle2").withDescription("categoryDescription1").build()
    );


    private SkillRepository skillRepository;
    private CategoryRepository categoryRepository;
    private CraftRepository craftRepository;

    @Autowired
    public DatabaseSeeder(SkillRepository skillRepository, CategoryRepository categoryRepository, final CraftRepository craftRepository) {
        this.skillRepository = skillRepository;
        this.categoryRepository = categoryRepository;
        this.craftRepository = craftRepository;
    }

    @Override
    public void run(String... args) {

        SKILLS.forEach(skill -> skillRepository.save(skill));
        CATEGORIES.forEach(category -> categoryRepository.save(category));
        CRAFTS.forEach(craft -> craftRepository.save(craft));
    }

}
