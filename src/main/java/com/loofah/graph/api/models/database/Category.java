package com.loofah.graph.api.models.database;

import org.springframework.data.annotation.Id;

import java.util.Objects;


public class Category {

    @Id
    private final String id;
    private final String title;
    private final String description;

    public Category(final String id, final String title, final String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    /**
     * This enum contains the string representation
     * of the fields in this class. Therefore it also
     * represents the fields as they are stored in the database.
     */
    public enum CategoryFields {

        ID("id"),
        TITLE("title"),
        DESCRIPTION("description");

        String key;

        CategoryFields(String key) {
            this.key = key;
        }

        public String key() {
            return this.key;
        }
    }

    public static CategoryBuilder builder() {
        return new CategoryBuilder();
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


    public static class CategoryBuilder {

        private String id;
        private String title;
        private String description;

        public CategoryBuilder withId(final String id) {
            this.id = id;
            return this;
        }

        public CategoryBuilder withTitle(final String title) {
            this.title = title;
            return this;
        }

        public CategoryBuilder withDescription(final String description) {
            this.description = description;
            return this;
        }

        public Category build() {
            return new Category(this.id, this.title, this.description);
        }

    }
}
