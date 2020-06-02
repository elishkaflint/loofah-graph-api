package com.loofah.graph.api.models;

import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.models.database.Skill;

import java.util.List;

public class CategoryWithSkills extends Category {
    private List<Skill> skills;

    public CategoryWithSkills(int id, String title, List<Skill> skills) {
        super(id, title);
        this.skills = skills;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
