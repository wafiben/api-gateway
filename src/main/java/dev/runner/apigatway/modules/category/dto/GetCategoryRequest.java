package dev.runner.apigatway.modules.category.dto;

public class GetCategoryRequest {
    private String name;
    private String description;

    public GetCategoryRequest() {
    }

    public GetCategoryRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters & Setters
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
}