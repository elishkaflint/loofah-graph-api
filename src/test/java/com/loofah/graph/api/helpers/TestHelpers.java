package com.loofah.graph.api.helpers;

import com.loofah.graph.api.models.CategoryWithSkills;
import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.models.database.Skill;

import java.util.List;
import java.util.stream.Collectors;

public class TestHelpers {

    public static String SKILL_ID = "1";
    public static String SKILL_TITLE = "title1";
    public static String SKILL_DESCRIPTION = "description1";

    public static int CATEGORY_ID = 1;
    public static String CATEGORY_TITLE = "1";

    public static Skill.SkillBuilder getDefaultSkillBuilder() {
        return Skill.builder().withId(SKILL_ID).withTitle(SKILL_TITLE).withDescription(SKILL_DESCRIPTION);
    }

    public static Category.CategoryBuilder getDefaultCategoryBuilder() {
        return Category.builder().withId(CATEGORY_ID).withTitle(CATEGORY_TITLE);
    }

    public static Category getCategoryById(List<Category> categories, int id) {
        return categories.stream()
                .filter(category -> category.getId() == id)
                .findFirst()
                .get();
    }

    public static CategoryWithSkills getCategoryResponseById(List<CategoryWithSkills> categories, int id) {
        return categories.stream()
                .filter(category -> category.getId() == id)
                .findFirst()
                .get();
    }

    public static List<Skill> filterSkillsByCategoryId(List<Skill> skills, int id) {
        return skills.stream().filter(skill -> skill.getCategoryId() == id).collect(Collectors.toList());

    }


    public static boolean allSkillIdsMatchAssociatedCategory(List<CategoryWithSkills> categoryWithSkills) {
        return categoryWithSkills.stream().map(category ->
                category.getSkills()
                        .stream()
                        .allMatch(skill -> skill.getCategoryId() == category.getId())
        ).reduce(Boolean::logicalAnd).orElse(false);
    }

}
