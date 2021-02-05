package com.loofah.graph.api.models.dto;

import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.models.database.Skill;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsLast;

public class SkillDTO implements Comparable<SkillDTO> {

    @Id
    private final String id;
    private final String topic;
    private final String description;
    private final Category category;
    private final Grade grade;
    private final List<Craft> crafts;
    private final String examples;

    public SkillDTO(final String id,
                    final String topic,
                    final String description,
                    final Category category,
                    final Grade grade,
                    final List<Craft> crafts,
                    final String examples) {
        this.id = id;
        this.topic = topic;
        this.description = description;
        this.category = category;
        this.grade = grade;
        this.crafts = crafts;
        this.examples = examples;
    }

    public SkillDTO(final Skill skill, final Category category, final Grade grade, List<Craft> crafts) {
        this.id = skill.getId();
        this.topic = skill.getTopic();
        this.description = skill.getDescription();
        this.category = category;
        this.grade = grade;
        this.crafts = crafts;
        this.examples = skill.getExamples();
    }

    public static SkillDTO.SkillDTOBuilder builder() {
        return new SkillDTO.SkillDTOBuilder();
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

    public Category getCategory() {
        return category;
    }

    public Grade getGrade() {
        return grade;
    }

    public List<Craft> getCrafts() {
        return crafts;
    }

    @Override
    public String toString() {
        return "SkillDTO{" +
                "id='" + id + '\'' +
                ", topic='" + topic + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", grade='" + grade + '\'' +
                ", crafts=" + crafts +
                ", examples='" + examples + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SkillDTO skillDTO = (SkillDTO) o;
        return Objects.equals(id, skillDTO.id) &&
                Objects.equals(topic, skillDTO.topic) &&
                Objects.equals(description, skillDTO.description) &&
                Objects.equals(category, skillDTO.category) &&
                Objects.equals(grade, skillDTO.grade) &&
                Objects.equals(crafts, skillDTO.crafts) &&
                Objects.equals(examples, skillDTO.examples);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topic, description, category, grade, crafts, examples);
    }

    // Compares two SkillDTOs first by grade, then by category, then by title (alphabetical order). See the Grade and
    // Category classes for details of how objects of these types are compared. At each stage, objects with a null value
    // for the field being compared will be placed last in that comparison.
    @Override
    public int compareTo(@NotNull final SkillDTO otherSkill) {
        return Comparator.comparing(SkillDTO::getGrade, nullsLast(naturalOrder()))
                .thenComparing(SkillDTO::getCategory, nullsLast(naturalOrder()))
                .thenComparing(SkillDTO::getTopic, nullsLast(naturalOrder()))
                .compare(this, otherSkill);
    }

    /**
     * This enum contains the string representation
     * of the fields in this class.
     */
    public enum SkillDTOFields {

        ID("id"),
        TOPIC("topic"),
        DESCRIPTION("description"),
        CATEGORY("category"),
        GRADE("grade"),
        CRAFTS("crafts"),
        EXAMPLES("examples");

        String key;

        SkillDTOFields(final String key) {
            this.key = key;
        }

        public String key() {
            return this.key;
        }
    }

    public static class SkillDTOBuilder {

        private String id;
        private String topic;
        private String description;
        private Category category;
        private Grade grade;
        private List<Craft> crafts;
        private String examples;

        public SkillDTOBuilder withId(final String id) {
            this.id = id;
            return this;
        }

        public SkillDTOBuilder withTopic(final String topic) {
            this.topic = topic;
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

        public SkillDTOBuilder withExamples(final String examples) {
            this.examples = examples;
            return this;
        }

        public SkillDTO build() {
            return new SkillDTO(this.id, this.topic, this.description, this.category, this.grade, this.crafts, this.examples);
        }

    }
}
