package com.loofah.graph.api.models;

import org.springframework.data.annotation.Id;

public class Skill {

    @Id
    private String id;

    private String title;

    private String description;


    public Skill(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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

    public static class SkillBuilder {

        private String id;
        private String title;
        private String description;


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

        public Skill build() {
            return new Skill(this.id, this.title, this.description);
        }

    }
}
