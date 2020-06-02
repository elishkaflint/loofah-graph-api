package com.loofah.graph.api.models;

public class Request {

    private String query;

    public Request() {
    }

    public Request(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

}
