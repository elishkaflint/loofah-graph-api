package com.loofah.graph.api.models.database;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Skill {

    @Id
    private final String id;
    private final String title;
    private final String description;
    private final String categoryId;
    private final String gradeId;
    private final List<String> craftIds;
    private final String examples;
    private final Category category;
    private final Grade grade;

    public Skill(final String id,
                 final String title,
                 final String description,
                 final String categoryId,
                 final String gradeId,
                 final List<String> craftIds,
                 final String examples, final Category category, final Grade grade) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.categoryId = categoryId;
        this.gradeId = gradeId;
        this.craftIds = craftIds;
        this.examples = examples;
        this.category = category;
        this.grade = grade;
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

    public String getCategoryId() {
        return categoryId;
    }

    public String getGradeId() {
        return gradeId;
    }

    public List<String> getCraftIds() {
        return craftIds;
    }

    public static class SkillBuilder {

        private String id;
        private String title;
        private String description;
        private String categoryId;
        private String gradeId;
        private List<String> craftIds;
        private String examples;
        private Category category;
        private Grade grade;

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

        public SkillBuilder withCategoryId(final String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public SkillBuilder withGradeId(final String gradeId) {
            this.gradeId = gradeId;
            return this;
        }

        public SkillBuilder withCraftIds(final List<String> craftIds) {
            this.craftIds = craftIds;
            return this;
        }

        public SkillBuilder withExamples(final String examples) {
            this.examples = examples;
            return this;
        }

        public SkillBuilder withCategory(final Category category) {
            this.category = category;
            return this;
        }

        public SkillBuilder withGrade(final Grade grade) {
            this.grade = grade;
            return this;
        }

        public Skill build() {
            return new Skill(this.id, this.title, this.description, this.categoryId, this.gradeId, this.craftIds, this.examples, category, grade);
        }

    }
}
