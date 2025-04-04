package com.example.model;

public class Project {
    private String name;
    private String description;
    private String link;

    // Constructor
    public Project(String name, String description, String link) {
        this.name = name;
        this.description = description;
        this.link = link;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
