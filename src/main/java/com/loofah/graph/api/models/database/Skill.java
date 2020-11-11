package com.loofah.graph.api.models.database;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsLast;

public class Skill implements Comparable<Skill> {

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

    public Category getCategory() {
        return category;
    }

    public Grade getGrade() {
        return grade;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Skill skill = (Skill) o;
        return Objects.equals(id, skill.id) &&
                Objects.equals(title, skill.title) &&
                Objects.equals(description, skill.description) &&
                Objects.equals(categoryId, skill.categoryId) &&
                Objects.equals(gradeId, skill.gradeId) &&
                Objects.equals(craftIds, skill.craftIds) &&
                Objects.equals(examples, skill.examples) &&
                Objects.equals(category, skill.category) &&
                Objects.equals(grade, skill.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, categoryId, gradeId, craftIds, examples, category, grade);
    }

    @Override
    public int compareTo(@NotNull final Skill otherSkill) {
        return Comparator.comparing(Skill::getGrade, nullsLast(naturalOrder()))
                .thenComparing(Skill::getCategory, nullsLast(naturalOrder()))
                .thenComparing(Skill::getTitle, nullsLast(naturalOrder()))
                .compare(this, otherSkill);
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
