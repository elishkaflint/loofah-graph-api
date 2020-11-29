package com.loofah.graph.api.models.database;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Skill {

    @Id
    private final String id;
    private final String title;
    private final String description;
    private final String categoryTitle;
    private final String gradeTitle;
    private final List<String> craftTitles;
    private final String examples;

    public Skill(final String id,
                 final String title,
                 final String description,
                 final String categoryTitle,
                 final String gradeTitle,
                 final List<String> craftTitles,
                 final String examples) {
        this.id = id;
        this.title = title;
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

    public String getTitle() {
        return title;
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
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", categoryTitle='" + categoryTitle + '\'' +
                ", gradeTitle='" + gradeTitle + '\'' +
                ", craftTitles=" + craftTitles +
                ", examples='" + examples + '\'' +
                '}';
    }

    public static class SkillBuilder {

        private String id;
        private String title;
        private String description;
        private String categoryTitle;
        private String gradeTitle;
        private List<String> craftTitles;
        private String examples;

        public SkillBuilder withId(final String id) {
            this.id = id;
            return this;
        }

        public SkillBuilder withTitle(final String title) {
            this.title = title;
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
            return new Skill(this.id, this.title, this.description, this.categoryTitle, this.gradeTitle, this.craftTitles, this.examples);
        }

    }
}
