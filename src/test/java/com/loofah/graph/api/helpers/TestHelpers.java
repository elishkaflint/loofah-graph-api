package com.loofah.graph.api.helpers;

import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.models.dto.SkillDTO;

import java.util.Collections;
import java.util.List;

public class TestHelpers {

    public static final String SKILL_ID_VALUE_1 = "1";
    public static final String SKILL_TITLE_VALUE_1 = "title1";
    public static final String SKILL_DESCRIPTION_VALUE_1 = "description1";

    public static final String CATEGORY_ID_VALUE_1 = "1";
    public static final String CATEGORY_TITLE_VALUE_1 = "technical";
    public static final String CATEGORY_TITLE_DESCRIPTION_1 = "technicalDescription";

    public static final String CRAFT_ID_VALUE_1 = "1";
    public static final String CRAFT_TITLE_VALUE_1 = "architecture";
    public static final String CRAFT_DESCRIPTION_VALUE_1 = "architectureDescription";
    public static final List<String> LEAD_1 = Collections.singletonList("lead1");
    public static final String DEV_SERVICES_PAGE_1 = "page1";
    public static final List<String> SLACK_CHANNELS_1 = Collections.emptyList();

    public static final String GRADE_ID_VALUE_1 = "1";
    public static final String GRADE_TITLE_VALUE_1 = "analystDeveloper";
    public static final String GRADE_DESCRIPTION_VALUE_1 = "analystDeveloperDescription";
    public static final String GRADE_HR_CODE_VALUE_1 = "M1";


    public static SkillDTO getDefaultSkillDTO() {
        return getDefaultSkillDTOBuilder().build();
    }

    public static SkillDTO.SkillDTOBuilder getDefaultSkillDTOBuilder() {
        return SkillDTO.builder()
                .withId(SKILL_ID_VALUE_1)
                .withTitle(SKILL_TITLE_VALUE_1)
                .withDescription(SKILL_DESCRIPTION_VALUE_1)
                .withCategory(getDefaultCategoryBuilder().build())
                .withGrade(getDefaultGradeBuilder().build())
                .withCrafts(Collections.singletonList(getDefaultCraftBuilder().build())
        );
    }

    public static Skill.SkillBuilder getDefaultSkillBuilder() {
        return Skill.builder()
                .withId(SKILL_ID_VALUE_1)
                .withTitle(SKILL_TITLE_VALUE_1)
                .withDescription(SKILL_DESCRIPTION_VALUE_1)
                .withCategoryTitle(CATEGORY_TITLE_VALUE_1)
                .withGradeTitle(GRADE_TITLE_VALUE_1)
                .withCraftTitles(Collections.singletonList(CRAFT_TITLE_VALUE_1));
    }

    public static Category.CategoryBuilder getDefaultCategoryBuilder() {
        return Category.builder()
                .withId(CATEGORY_ID_VALUE_1)
                .withTitle(CATEGORY_TITLE_VALUE_1)
                .withDescription(CATEGORY_TITLE_DESCRIPTION_1);
    }

    public static Craft.Builder getDefaultCraftBuilder() {
        return Craft.builder()
                .withId(CRAFT_ID_VALUE_1)
                .withTitle(CRAFT_TITLE_VALUE_1)
                .withDescription(CRAFT_DESCRIPTION_VALUE_1)
                .withLead(LEAD_1)
                .withDevServicesPage(DEV_SERVICES_PAGE_1)
                .withSlackChannels(SLACK_CHANNELS_1);
    }

    public static Grade.GradeBuilder getDefaultGradeBuilder() {
        return Grade.builder()
                .withId(GRADE_ID_VALUE_1)
                .withTitle(GRADE_TITLE_VALUE_1)
                .withDescription(GRADE_DESCRIPTION_VALUE_1)
                .withHrCode(GRADE_HR_CODE_VALUE_1);
    }

}
