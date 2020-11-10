package com.loofah.graph.api.models.DTO;

import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.models.database.Skill;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SkillDTO {

    @Id
    private final String id;
    private final String title;
    private final String description;
    private final Category category;
    private final String gradeId;
    private final List<String> craftIds;
    private final String examples;

    public SkillDTO(final String id,
                 final String title,
                 final String description,
                 final Category category,
                 final String gradeId,
                 final List<String> craftIds,
                 final String examples) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.gradeId = gradeId;
        this.craftIds = craftIds;
        this.examples = examples;
    }

    public SkillDTO(final Skill skill, Category category) {
        this.id = skill.getId();
        this.title = skill.getTitle();
        this.description = skill.getDescription();
        this.gradeId = skill.getGradeId();
        this.craftIds = skill.getCraftIds();
        this.examples = skill.getExamples();
        this.category = category;
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
        GRADE_ID("gradeId"),
        CRAFT_IDS("craftIds"),
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

    public String getGradeId() { return gradeId; }

    public List<String> getCraftIds() {
        return craftIds;
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
                Objects.equals(gradeId, skillDTO.gradeId) &&
                Objects.equals(craftIds, skillDTO.craftIds) &&
                Objects.equals(examples, skillDTO.examples);
    }

    @Override
    public String toString() {
        return "SkillDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", gradeId='" + gradeId + '\'' +
                ", craftIds=" + craftIds +
                ", examples='" + examples + '\'' +
                '}';
    }

    public static class SkillDTOBuilder {

        private String id;
        private String title;
        private String description;
        private Category category;
        private String gradeId;
        private List<String> craftIds;
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

        public SkillDTOBuilder withGradeId(final String gradeId) {
            this.gradeId = gradeId;
            return this;
        }

        public SkillDTOBuilder withCraftIds(final List<String> craftIds) {
            this.craftIds = craftIds;
            return this;
        }

        public  SkillDTOBuilder withExamples(final String examples) {
            this.examples = examples;
            return this;
        }

        public SkillDTO build() {
            return new SkillDTO(this.id, this.title, this.description, this.category, this.gradeId, this.craftIds, this.examples);
        }

    }
}
