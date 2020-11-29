package com.loofah.graph.api.models.database;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;

import java.util.*;

import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsLast;

public class Grade implements Comparable<Grade> {

    private static final List<String> TITLES_BY_SENIORITY = Collections.unmodifiableList(Arrays.asList(
            "analystDeveloper",
            "developer",
            "seniorDeveloper",
            "technicalLead",
            "seniorTechnicalLead",
            "technicalDirector",
            "partner"
    ));

    @Id
    private String id;
    private String title;
    private String description;
    private String hrCode;

    public Grade(final String id, final String title, final String description, final String hrCode) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.hrCode = hrCode;
    }

    public static Grade.GradeBuilder builder() {
        return new Grade.GradeBuilder();
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getHrCode() {
        return hrCode;
    }

    public void setHrCode(final String hrCode) {
        this.hrCode = title;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Grade grade = (Grade) o;
        return Objects.equals(id, grade.id) &&
                Objects.equals(title, grade.title) &&
                Objects.equals(description, grade.description) &&
                Objects.equals(hrCode, grade.hrCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, hrCode);
    }

    // Compares two Grade objects based on the rank order of their titles. See the TITLES_BY_SENIORITY field for the
    // current rank order from junior to senior. If two titles have the same rank (not currently possible), grades are
    // then compared alphabetically by title, with null titles last.
    @Override
    public int compareTo(@NotNull final Grade otherGrade) {
        return Comparator.comparing(Grade::getRank)
                .thenComparing(Grade::getTitle, nullsLast(naturalOrder()))
                .compare(this, otherGrade);
    }

    private int getRank() {
        final int rank = TITLES_BY_SENIORITY.indexOf(title);
        return rank < 0 ? Integer.MAX_VALUE : rank;
    }

    /**
     * This enum contains the string representation
     * of the fields in this class. Therefore it also
     * represents the fields as they are stored in the database.
     */
    public enum GradeFields {

        ID("id"),
        TITLE("title"),
        DESCRIPTION("description"),
        HR_CODE("hrCode");

        String key;

        GradeFields(final String key) {
            this.key = key;
        }

        public String key() {
            return this.key;
        }
    }

    public static class GradeBuilder {

        private String id;
        private String title;
        private String description;
        private String hrCode;

        public GradeBuilder withId(final String id) {
            this.id = id;
            return this;
        }

        public GradeBuilder withTitle(final String title) {
            this.title = title;
            return this;
        }

        public GradeBuilder withDescription(final String description) {
            this.description = description;
            return this;
        }

        public GradeBuilder withHrCode(final String hrCode) {
            this.hrCode = hrCode;
            return this;
        }

        public Grade build() {
            return new Grade(this.id, this.title, this.description, this.hrCode);
        }

    }
}
