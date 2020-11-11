package com.loofah.graph.api.models.database;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;

import java.util.Comparator;
import java.util.Objects;

import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsLast;


public class Category implements Comparable<Category> {

    @Id
    private final String id;
    private final String title;
    private final String description;

    public Category(final String id, final String title, final String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(title, category.title) &&
                Objects.equals(description, category.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description);
    }

    @Override
    public int compareTo(@NotNull final Category otherCategory) {
        final Comparator<Category> titleComparator = nullsLast(
                Comparator.comparing(Category::getTitle, nullsLast(naturalOrder()))
        );
        return titleComparator.compare(this, otherCategory);
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
