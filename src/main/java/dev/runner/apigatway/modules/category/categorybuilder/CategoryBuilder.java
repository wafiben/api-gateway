package dev.runner.apigatway.modules.category.categorybuilder;

import dev.runner.apigatway.modules.category.dto.CategoryDto;

public class CategoryBuilder {
    private String id;
    private String name;

    public CategoryBuilder id(String id) {
        this.id = id;
        return this;
    }

    public CategoryBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CategoryDto build() {
        return new CategoryDto(id, name);
    }
}