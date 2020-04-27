package com.loofah.graph.api.models;

public class SkillRequest {

    private String query;

    public SkillRequest() {
    }

    public SkillRequest(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

}
