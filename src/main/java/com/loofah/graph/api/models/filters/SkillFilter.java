package com.loofah.graph.api.models.filters;

import java.util.List;

public class SkillFilter {

    private String categoryTitle;
    private String gradeTitle;
    private List<String> craftTitles;

    public SkillFilter() {}

    public SkillFilter(String categoryTitle, String gradeTitle, List<String> craftTitles) {
        this.categoryTitle = categoryTitle;
        this.gradeTitle = gradeTitle;
        this.craftTitles = craftTitles;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public String getGradeTitle() {
        return gradeTitle;
    }

    public List<String> getCraftTitles() {
        return craftTitles;
    }

    public static SkillFilterBuilder builder() {
        return new SkillFilterBuilder();
    }

    public static class SkillFilterBuilder {

        private String categoryTitle;
        private String gradeTitle;
        private List<String> craftTitles;

        public SkillFilterBuilder withCategoryTitle(String categoryTitle) {
            this.categoryTitle = categoryTitle;
            return this;
        }

        public SkillFilterBuilder withGradeTitle(String gradeTitle) {
            this.gradeTitle = gradeTitle;
            return this;
        }

        public SkillFilterBuilder withCraftTitles(List<String> craftTitles) {
            this.craftTitles = craftTitles;
            return this;
        }

        public SkillFilter build() {
            return new SkillFilter(this.categoryTitle, this.gradeTitle, this.craftTitles);
        }

    }
}