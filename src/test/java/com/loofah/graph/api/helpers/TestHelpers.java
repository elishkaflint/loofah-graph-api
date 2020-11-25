package com.loofah.graph.api.helpers;

import com.loofah.graph.api.models.DTO.SkillDTO;
import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.models.database.Skill;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class TestHelpers {

    public static final String SKILL_ID_VALUE_1 = "1";
    public static final String SKILL_TITLE_VALUE_1 = "title1";
    public static final String SKILL_DESCRIPTION_VALUE_1 = "description1";

    public static final String CATEGORY_ID_VALUE_1 = "1";
    public static final String CATEGORY_TITLE_VALUE_1 = "1";

    public static final String CRAFT_ID_VALUE_1 = "1";
    public static final String CRAFT_TITLE_VALUE_1 = "craftTitle1";
    public static final String CRAFT_DESCRIPTION_VALUE_1 = "craftDescription1";
    public static final List<String> LEAD = Collections.singletonList("lead1");
    public static final String PAGE = "page1";
    public static final List<String> SLACK_CHANNELS = Collections.emptyList();

    public static final String GRADE_ID_VALUE_1 = "1";
    public static final String GRADE_TITLE_VALUE_1 = "GradeTitle1";
    public static final String GRADE_DESCRIPTION_VALUE_1 = "gradeDescription1";
    public static final String GRADE_HR_CODE_VALUE_1 = "M1";


    public static SkillDTO getDefaultSkillDTO() {
        return new SkillDTO(getDefaultSkillBuilder().build(), getDefaultCategoryBuilder().build(), getDefaultGradeBuilder().build());
    }

    public static Skill.SkillBuilder getDefaultSkillBuilder() {
        return Skill.builder()
                .withId(SKILL_ID_VALUE_1)
                .withTitle(SKILL_TITLE_VALUE_1)
                .withDescription(SKILL_DESCRIPTION_VALUE_1)
                .withCategoryId(CATEGORY_ID_VALUE_1)
                .withGradeId(GRADE_ID_VALUE_1);
    }

    public static Category.CategoryBuilder getDefaultCategoryBuilder() {
        return Category.builder()
                .withId(CATEGORY_ID_VALUE_1)
                .withTitle(CATEGORY_TITLE_VALUE_1);
    }

    public static Craft.Builder getDefaultCraftBuilder() {
        return Craft.builder()
                .withId(CRAFT_ID_VALUE_1)
                .withTitle(CRAFT_TITLE_VALUE_1)
                .withDescription(CRAFT_DESCRIPTION_VALUE_1)
                .withLead(LEAD)
                .withDevServicesPage(PAGE)
                .withSlackChannels(SLACK_CHANNELS);
    }

    public static Grade.GradeBuilder getDefaultGradeBuilder() {
        return Grade.builder()
                .withId(GRADE_ID_VALUE_1)
                .withTitle(GRADE_TITLE_VALUE_1)
                .withDescription(GRADE_DESCRIPTION_VALUE_1)
                .withHrCode(GRADE_HR_CODE_VALUE_1);
    }

}
