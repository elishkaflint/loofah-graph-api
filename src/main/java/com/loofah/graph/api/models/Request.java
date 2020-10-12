package com.loofah.graph.api.models;

import java.util.Map;

public class Request {

    private String query;
    private Map<String, Object> variables;

    public Request() {
    }

    public Request(String query, Map<String, Object> variables) {
        this.query = query;
        this.variables = variables;
    }

    public String getQuery() {
        return query;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }
}
