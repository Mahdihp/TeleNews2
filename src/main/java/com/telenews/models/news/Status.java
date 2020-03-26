package com.telenews.models.news;

public enum Status {

    Preview("PREVIEW"),
    Published("PUBLISHED"),
    Hidden("HIDDEN");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
