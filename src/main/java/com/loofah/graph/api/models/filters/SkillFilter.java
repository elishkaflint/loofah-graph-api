package com.loofah.graph.api.models.filters;

import java.util.List;

public class SkillFilter {

    private String categoryTitle;
    private List<String> gradeTitles;
    private List<String> craftTitles;

    public SkillFilter() {}

    public SkillFilter(String categoryTitle, List<String> gradeTitles, List<String> craftTitles) {
        this.categoryTitle = categoryTitle;
        this.gradeTitles = gradeTitles;
        this.craftTitles = craftTitles;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public List<String> getGradeTitles() {
        return gradeTitles;
    }

    public List<String> getCraftTitles() {
        return craftTitles;
    }

    public static SkillFilterBuilder builder() {
        return new SkillFilterBuilder();
    }

    public static class SkillFilterBuilder {

        private String categoryTitle;
        private List<String> gradeTitles;
        private List<String> craftTitles;

        public SkillFilterBuilder withCategoryTitle(String categoryTitle) {
            this.categoryTitle = categoryTitle;
            return this;
        }

        public SkillFilterBuilder withGradeTitles(List<String> gradeTitles) {
            this.gradeTitles = gradeTitles;
            return this;
        }

        public SkillFilterBuilder withCraftTitles(List<String> craftTitles) {
            this.craftTitles = craftTitles;
            return this;
        }

        public SkillFilter build() {
            return new SkillFilter(this.categoryTitle, this.gradeTitles, this.craftTitles);
        }

    }
}