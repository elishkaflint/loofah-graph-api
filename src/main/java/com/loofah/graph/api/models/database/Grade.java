package com.loofah.graph.api.models.database;

import org.springframework.data.annotation.Id;

public class Grade {

    @Id
    private String id;
    private String title;
    private String description;
    private String hrCode;

    public Grade(String id, String title, String description, String hrCode) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.hrCode = hrCode;
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

        GradeFields(String key) {
            this.key = key;
        }

        public String key() {
            return this.key;
        }
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

    public String getHrCode() {
        return hrCode;
    }

    public void setHrCode(String hrCode) { this.hrCode = title; }

    public static Grade.GradeBuilder builder() {
        return new Grade.GradeBuilder();
    }

    public static class GradeBuilder {

        private String id;
        private String title;
        private String description;
        private String hrCode;

        public GradeBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public GradeBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public GradeBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public GradeBuilder withHrCode(String hrCode) {
            this.hrCode = hrCode;
            return this;
        }

        public Grade build() {
            return new Grade(this.id, this.title, this.description, this.hrCode);
        }

    }
}
