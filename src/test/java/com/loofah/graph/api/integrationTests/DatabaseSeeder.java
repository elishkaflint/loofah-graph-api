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

    public static final List<Skill> SKILLS = Collections.unmodifiableList(Arrays.asList(
            Skill.builder()
                    .withId("4")
                    .withGradeTitle("developer")
                    .withCategoryTitle("technical")
                    .withTopic("topic4")
                    .withDescription("fourth")
                    .withCraftTitles(Collections.singletonList("platform"))
                    .withExamples("example")
                    .build(),
            Skill.builder()
                    .withId("3")
                    .withGradeTitle("developer")
                    .withCategoryTitle("technical")
                    .withTopic("topic3")
                    .withDescription("third")
                    .withCraftTitles(Collections.singletonList("platform"))
                    .withExamples("example")
                    .build(),
            Skill.builder()
                    .withId("2")
                    .withGradeTitle("developer")
                    .withCategoryTitle("delivery")
                    .withTopic("topic2")
                    .withDescription("second")
                    .withCraftTitles(Collections.singletonList("mobile"))
                    .withExamples("example")
                    .build(),
            Skill.builder()
                    .withId("1")
                    .withGradeTitle("analystDeveloper")
                    .withCategoryTitle("delivery")
                    .withTopic("topic1")
                    .withDescription("first")
                    .withCraftTitles(Arrays.asList("architecture", "mobile"))
                    .withExamples("example")
                    .build()
    ));

    public static final List<Category> CATEGORIES = Collections.unmodifiableList(Arrays.asList(
            Category.builder()
                    .withId("1")
                    .withTitle("technical")
                    .withDescription("categoryDescription1")
                    .build(),
            Category.builder()
                    .withId("2")
                    .withTitle("delivery")
                    .withDescription("categoryDescription1")
                    .build()
    ));

    public static final List<Grade> GRADES = Collections.unmodifiableList(Arrays.asList(
            Grade.builder()
                    .withId("1")
                    .withTitle("analystDeveloper")
                    .withDescription("gradeDescription1")
                    .withHrCode("M1")
                    .build(),
            Grade.builder()
                    .withId("2")
                    .withTitle("developer")
                    .withDescription("gradeDescription2")
                    .withHrCode("M2")
                    .build()
    ));

    public static final List<Craft> CRAFTS = Collections.unmodifiableList(Arrays.asList(
            Craft.builder()
                    .withId("1")
                    .withTitle("architecture")
                    .withDescription("craftDescription1")
                    .withLead(Collections.singletonList("lead1"))
                    .withDevServicesPage("page1")
                    .withSlackChannels(Collections.emptyList())
                    .build(),
            Craft.builder()
                    .withId("2")
                    .withTitle("mobile")
                    .withDescription("craftDescription2")
                    .withLead(Collections.singletonList("lead2"))
                    .withDevServicesPage("page2")
                    .withSlackChannels(Collections.emptyList())
                    .build(),
            Craft.builder()
                    .withId("3")
                    .withTitle("platform")
                    .withDescription("craftDescription3")
                    .withLead(Collections.singletonList("lead3"))
                    .withDevServicesPage("page3")
                    .withSlackChannels(Collections.emptyList())
                    .build()
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
