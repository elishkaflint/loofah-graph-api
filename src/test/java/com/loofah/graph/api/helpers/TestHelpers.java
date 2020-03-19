package com.loofah.graph.api.helpers;

import com.loofah.graph.api.models.Skill;

public class TestHelpers {

    public static String ID = "1";
    public static String TITLE = "title1";
    public static String DESCRIPTION = "description1";

    public static Skill.SkillBuilder getDefaultSkillBuilder() {
        return Skill.builder().withId(ID).withTitle(TITLE).withDescription(DESCRIPTION);
    }

}
