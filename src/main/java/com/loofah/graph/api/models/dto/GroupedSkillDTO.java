package com.loofah.graph.api.models.dto;

import java.util.List;

public class GroupedSkillDTO {

    private final String title;
    private final List<SkillDTO> skills;

    public GroupedSkillDTO(String title, List<SkillDTO> skills) {
        this.title = title;
        this.skills = skills;
    }

    public String getTitle() {
        return title;
    }

    public List<SkillDTO> getSkills() {
        return skills;
    }

}
