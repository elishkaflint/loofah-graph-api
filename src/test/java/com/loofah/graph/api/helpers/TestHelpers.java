package com.loofah.graph.api.helpers;

import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.models.database.Skill;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestHelpers {

    public static String SKILL_ID = "1";
    public static String SKILL_TITLE = "title1";
    public static String SKILL_DESCRIPTION = "description1";

    public static String CATEGORY_ID = "1";
    public static String CATEGORY_TITLE = "1";

    public static final String CRAFT_ID = "1";
    public static final String CRAFT_TITLE = "craftTitle1";
    public static final String CRAFT_DESCRIPTION = "craftDescription1";
    public static final List<String> LEAD = Collections.singletonList("lead1");
    public static final String PAGE = "page1";
    public static final List<String> SLACK_CHANNELS = Collections.emptyList();

    public static Skill.SkillBuilder getDefaultSkillBuilder() {
        return Skill.builder().withId(SKILL_ID).withTitle(SKILL_TITLE).withDescription(SKILL_DESCRIPTION).withCategoryId(CATEGORY_ID);
    }

    public static Category.CategoryBuilder getDefaultCategoryBuilder() {
        return Category.builder().withId(CATEGORY_ID).withTitle(CATEGORY_TITLE);
    }

    public static Craft.Builder getDefaultCraftBuilder() {
        return Craft.builder().withId(CRAFT_ID).withTitle(CRAFT_TITLE).withDescription(CRAFT_DESCRIPTION).withLead(LEAD).withDevServicesPage(PAGE).withSlackChannels(SLACK_CHANNELS);
    }

}
