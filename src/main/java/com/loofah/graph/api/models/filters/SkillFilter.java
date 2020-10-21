package com.loofah.graph.api.models.filters;

public class SkillFilter {

    private String categoryId;
    private String gradeId;

    public SkillFilter() {}

    public SkillFilter(String categoryId, String gradeId) {
        this.categoryId = categoryId;
        this.gradeId = gradeId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getGradeId() {
        return gradeId;
    }

    public static SkillFilterBuilder builder() {
        return new SkillFilterBuilder();
    }

    public static class SkillFilterBuilder {

        private String categoryId;
        private String gradeId;

        public SkillFilterBuilder withCategoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public SkillFilterBuilder withGradeId(String gradeId) {
            this.gradeId = gradeId;
            return this;
        }

        public SkillFilter build() {
            return new SkillFilter(this.categoryId, this.gradeId);
        }

    }
}
