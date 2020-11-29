package com.loofah.graph.api.models.dto;

import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.models.database.Skill;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Objects;

public class SkillDTO {

    @Id
    private final String id;
    private final String title;
    private final String description;
    private final Category category;
    private final Grade grade;
    private final List<Craft> crafts;
    private final String examples;

    public SkillDTO(final String id,
                 final String title,
                 final String description,
                 final Category category,
                 final Grade grade,
                 final List<Craft> crafts,
                 final String examples) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.grade = grade;
        this.crafts = crafts;
        this.examples = examples;
    }

    public SkillDTO(final Skill skill, Category category, Grade grade, List<Craft> crafts) {
        this.id = skill.getId();
        this.title = skill.getTitle();
        this.description = skill.getDescription();
        this.category = category;
        this.grade = grade;
        this.crafts = crafts;
        this.examples = skill.getExamples();
    }

    /**
     * This enum contains the string representation
     * of the fields in this class.
     */
    public enum SkillDTOFields {

        ID("id"),
        TITLE("title"),
        DESCRIPTION("description"),
        CATEGORY("category"),
        GRADE("grade"),
        CRAFTS("crafts"),
        EXAMPLES("examples");

        String key;

        SkillDTOFields(String key) {
            this.key = key;
        }

        public String key() {
            return this.key;
        }
    }

    public static Skill.SkillBuilder builder() {
        return new Skill.SkillBuilder();
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

    public Category getCategory() {
        return category;
    }

    public Grade getGrade() { return grade; }

    public List<Craft> getCrafts() {
        return crafts;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillDTO skillDTO = (SkillDTO) o;
        return Objects.equals(id, skillDTO.id) &&
                Objects.equals(title, skillDTO.title) &&
                Objects.equals(description, skillDTO.description) &&
                Objects.equals(category, skillDTO.category) &&
                Objects.equals(grade, skillDTO.grade) &&
                Objects.equals(crafts, skillDTO.crafts) &&
                Objects.equals(examples, skillDTO.examples);
    }

    @Override
    public String toString() {
        return "SkillDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", grade='" + grade + '\'' +
                ", crafts=" + crafts +
                ", examples='" + examples + '\'' +
                '}';
    }

    public static class SkillDTOBuilder {

        private String id;
        private String title;
        private String description;
        private Category category;
        private Grade grade;
        private List<Craft> crafts;
        private String examples;

        public SkillDTOBuilder withId(final String id) {
            this.id = id;
            return this;
        }

        public SkillDTOBuilder withTitle(final String title) {
            this.title = title;
            return this;
        }

        public SkillDTOBuilder withDescription(final String description) {
            this.description = description;
            return this;
        }

        public SkillDTOBuilder withCategory(final Category category) {
            this.category = category;
            return this;
        }

        public SkillDTOBuilder withGrade(final Grade grade) {
            this.grade = grade;
            return this;
        }

        public SkillDTOBuilder withCrafts(final List<Craft> crafts) {
            this.crafts = crafts;
            return this;
        }

        public  SkillDTOBuilder withExamples(final String examples) {
            this.examples = examples;
            return this;
        }

        public SkillDTO build() {
            return new SkillDTO(this.id, this.title, this.description, this.category, this.grade, this.crafts, this.examples);
        }

    }
}
