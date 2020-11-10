package com.loofah.graph.api.models.filters;

import java.util.List;

public class SkillFilter {

    private String categoryId;
    private String gradeId;
    private List<String> craftIds;

    public SkillFilter() {}

    public SkillFilter(String categoryId, String gradeId, List<String> craftIds) {
        this.categoryId = categoryId;
        this.gradeId = gradeId;
        this.craftIds = craftIds;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getGradeId() {
        return gradeId;
    }

    public List<String> getCraftIds() {
        return craftIds;
    }

    public static SkillFilterBuilder builder() {
        return new SkillFilterBuilder();
    }

    public static class SkillFilterBuilder {

        private String categoryId;
        private String gradeId;
        private List<String> craftIds;

        public SkillFilterBuilder withCategoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public SkillFilterBuilder withGradeId(String gradeId) {
            this.gradeId = gradeId;
            return this;
        }

        public SkillFilterBuilder withCraftIds(List<String> craftIds) {
            this.craftIds = craftIds;
            return this;
        }

        public SkillFilter build() {
            return new SkillFilter(this.categoryId, this.gradeId, this.craftIds);
        }

    }
}