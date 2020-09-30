package com.loofah.graph.api.models.database;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Skill {

    @Id
    private String id;
    private String title;
    private String description;
    private String categoryId;
    private List<String> examples;

    public Skill(String id, String title, String description, String categoryId, List<String> examples) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.categoryId = categoryId;
        this.examples = examples;
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

    public List<String> getExamples() {
        return examples;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }

    public static SkillBuilder builder() {
        return new SkillBuilder();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public static class SkillBuilder {

        private String id;
        private String title;
        private String description;
        private String categoryId;
        private List<String> examples;

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

        public SkillBuilder withCategoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public SkillBuilder withExamples(List<String> examples) {
            this.examples = examples;
            return this;
        }

        public Skill build() {
            return new Skill(this.id, this.title, this.description, this.categoryId, this.examples);
        }

    }
}
