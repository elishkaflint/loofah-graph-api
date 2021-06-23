package com.loofah.graph.api.models.database;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Skill {

    @Id
    private final String id;
    private final String headline;
    private final String description;
    private final List<String> examples;
    private final String categoryTitle;
    private final String gradeTitle;
    private final List<String> craftTitles;

    public Skill(final String id,
                 final String headline,
                 final String description,
                 final List<String> examples,
                 final String categoryTitle,
                 final String gradeTitle,
                 final List<String> craftTitles) {
        this.id = id;
        this.headline = headline;
        this.description = description;
        this.examples = examples;
        this.categoryTitle = categoryTitle;
        this.gradeTitle = gradeTitle;
        this.craftTitles = craftTitles;
    }

    public static SkillBuilder builder() {
        return new SkillBuilder();
    }

    public String getId() {
        return id;
    }

    public String getHeadline() {
        return headline;
    }

    public List<String> getExamples() {
        return examples;
    }

    public String getDescription() {
        return description;
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
                ", headline='" + headline + '\'' +
                ", description='" + description + '\'' +
                ", examples='" + examples + '\'' +
                ", categoryTitle='" + categoryTitle + '\'' +
                ", gradeTitle='" + gradeTitle + '\'' +
                ", craftTitles=" + craftTitles +
                '}';
    }

    public static class SkillBuilder {

        private String id;
        private String headline;
        private String description;
        private List<String> examples;
        private String categoryTitle;
        private String gradeTitle;
        private List<String> craftTitles;

        public SkillBuilder withId(final String id) {
            this.id = id;
            return this;
        }

        public SkillBuilder withHeadline(final String headline) {
            this.headline = headline;
            return this;
        }

        public SkillBuilder withDescription(final String description) {
            this.description = description;
            return this;
        }

        public SkillBuilder withExamples(final List<String> examples) {
            this.examples = examples;
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

        public Skill build() {
            return new Skill(this.id, this.headline, this.description, this.examples, this.categoryTitle, this.gradeTitle, this.craftTitles);
        }

    }
}
