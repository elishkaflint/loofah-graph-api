package com.loofah.graph.api.models.database;

import org.springframework.data.annotation.Id;

public class Skill {

    @Id
    private String id;

    private String title;

    private String description;

    private int categoryId;

    public Skill(String id, String title, String description, int categoryId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static SkillBuilder builder() {
        return new SkillBuilder();
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public static class SkillBuilder {

        private String id;
        private String title;
        private String description;
        private int categoryId;


        public SkillBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public SkillBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public SkillBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public SkillBuilder withCategoryId(int categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Skill build() {
            return new Skill(this.id, this.title, this.description, this.categoryId);
        }

    }
}
