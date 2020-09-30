package com.loofah.graph.api.models.database;
import org.springframework.data.annotation.Id;


public class Category {

    @Id
    private String id;
    private String title;
    private String description;

    public Category(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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

    public static CategoryBuilder builder() {
        return new CategoryBuilder();
    }

    public static class CategoryBuilder {

        private String id;
        private String title;
        private String description;

        public CategoryBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public CategoryBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public CategoryBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Category build() {
            return new Category(this.id, this.title, this.description);
        }

    }
}
