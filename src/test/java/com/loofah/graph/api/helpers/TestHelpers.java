package com.loofah.graph.api.helpers;

import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.models.database.Skill;

public class TestHelpers {

    public static String SKILL_ID = "1";
    public static String SKILL_TITLE = "title1";
    public static String SKILL_DESCRIPTION = "description1";

    public static String CATEGORY_ID = "1";
    public static String CATEGORY_TITLE = "1";

    public static Skill.SkillBuilder getDefaultSkillBuilder() {
        return Skill.builder().withId(SKILL_ID).withTitle(SKILL_TITLE).withDescription(SKILL_DESCRIPTION).withCategoryId(CATEGORY_ID);
    }

    public static Category.CategoryBuilder getDefaultCategoryBuilder() {
        return Category.builder().withId(CATEGORY_ID).withTitle(CATEGORY_TITLE);
    }

}
