package com.loofah.graph.api.models.database;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Skill {

    @Id
    private final String id;
    private final String topic;
    private final String description;
    private final String categoryTitle;
    private final String gradeTitle;
    private final List<String> craftTitles;
    private final String examples;

    public Skill(final String id,
                 final String topic,
                 final String description,
                 final String categoryTitle,
                 final String gradeTitle,
                 final List<String> craftTitles,
                 final String examples) {
        this.id = id;
        this.topic = topic;
        this.description = description;
        this.categoryTitle = categoryTitle;
        this.gradeTitle = gradeTitle;
        this.craftTitles = craftTitles;
        this.examples = examples;
    }

    public static SkillBuilder builder() {
        return new SkillBuilder();
    }

    public String getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public String getDescription() {
        return description;
    }

    public String getExamples() {
        return examples;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public String getGradeTitle() {
        return gradeTitle;
    }

    public List<String> getCraftTitles() {
        return craftTitles;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id='" + id + '\'' +
                ", topic='" + topic + '\'' +
                ", description='" + description + '\'' +
                ", categoryTitle='" + categoryTitle + '\'' +
                ", gradeTitle='" + gradeTitle + '\'' +
                ", craftTitles=" + craftTitles +
                ", examples='" + examples + '\'' +
                '}';
    }

    public static class SkillBuilder {

        private String id;
        private String topic;
        private String description;
        private String categoryTitle;
        private String gradeTitle;
        private List<String> craftTitles;
        private String examples;

        public SkillBuilder withId(final String id) {
            this.id = id;
            return this;
        }

        public SkillBuilder withTopic(final String topic) {
            this.topic = topic;
            return this;
        }

        public SkillBuilder withDescription(final String description) {
            this.description = description;
            return this;
        }

        public SkillBuilder withCategoryTitle(final String categoryTitle) {
            this.categoryTitle = categoryTitle;
            return this;
        }

        public SkillBuilder withGradeTitle(final String gradeTitle) {
            this.gradeTitle = gradeTitle;
            return this;
        }

        public SkillBuilder withCraftTitles(final List<String> craftTitles) {
            this.craftTitles = craftTitles;
            return this;
        }

        public SkillBuilder withExamples(final String examples) {
            this.examples = examples;
            return this;
        }

        public Skill build() {
            return new Skill(this.id, this.topic, this.description, this.categoryTitle, this.gradeTitle, this.craftTitles, this.examples);
        }

    }
}
