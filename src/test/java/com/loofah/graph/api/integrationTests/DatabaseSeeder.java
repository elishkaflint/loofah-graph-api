package com.loofah.graph.api.integrationTests;

import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.repositories.CategoryRepository;
import com.loofah.graph.api.repositories.CraftRepository;
import com.loofah.graph.api.repositories.GradeRepository;
import com.loofah.graph.api.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class DatabaseSeeder {

    public static final List<Craft> CRAFTS = Collections.unmodifiableList(Arrays.asList(
            Craft.builder().withId("1").withTitle("craftTitle1").withDescription("craftDescription1").withLead(Collections.singletonList("lead1")).withDevServicesPage("page1").withSlackChannels(Collections.emptyList()).build(),
            Craft.builder().withId("2").withTitle("craftTitle2").withDescription("craftDescription2").withLead(Collections.singletonList("lead2")).withDevServicesPage("page2").withSlackChannels(Collections.emptyList()).build()
    ));
    public static final List<Skill> SKILLS = Collections.unmodifiableList(Arrays.asList(
            Skill.builder().withId("1").withTitle("title1").withDescription("description1").withGradeId("1").withCategoryId("1").withCraftIds(Arrays.asList("1", "2")).withExamples("example").build(),
            Skill.builder().withId("2").withTitle("title2").withDescription("description2").withGradeId("2").withCategoryId("1").withCraftIds(Collections.singletonList("2")).withExamples("example").build(),
            Skill.builder().withId("3").withTitle("title3").withDescription("description3").withGradeId("2").withCategoryId("2").withCraftIds(Collections.singletonList("3")).withExamples("example").build()
    ));

    public static final List<Category> CATEGORIES = Collections.unmodifiableList(Arrays.asList(
            Category.builder().withId("1").withTitle("categoryTitle1").withDescription("categoryDescription1").build(),
            Category.builder().withId("2").withTitle("categoryTitle2").withDescription("categoryDescription1").build()
    ));

    public static final List<Grade> GRADES = Collections.unmodifiableList(Arrays.asList(
            Grade.builder().withId("1").withTitle("GradeTitle1").withDescription("gradeDescription1").withHrCode("M1").build(),
            Grade.builder().withId("2").withTitle("GradeTitle2").withDescription("gradeDescription2").withHrCode("M2").build()
    ));


    private final SkillRepository skillRepository;
    private final CategoryRepository categoryRepository;
    private final CraftRepository craftRepository;
    private final GradeRepository gradeRepository;


    @Autowired
    public DatabaseSeeder(final SkillRepository skillRepository,
                          final CategoryRepository categoryRepository,
                          final CraftRepository craftRepository,
                          final GradeRepository gradeRepository) {
        this.skillRepository = skillRepository;
        this.categoryRepository = categoryRepository;
        this.craftRepository = craftRepository;
        this.gradeRepository = gradeRepository;

    }

    public void setUp() {
        SKILLS.forEach(skillRepository::save);
        CATEGORIES.forEach(categoryRepository::save);
        CRAFTS.forEach(craftRepository::save);
        GRADES.forEach(gradeRepository::save);
    }

}
