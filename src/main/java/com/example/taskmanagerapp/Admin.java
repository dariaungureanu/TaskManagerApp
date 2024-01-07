package com.example.taskmanagerapp;

public class Admin {
    private String project;
    private String category;

    public Admin(String project, String category) {
        this.project = project;
        this.category = category;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
