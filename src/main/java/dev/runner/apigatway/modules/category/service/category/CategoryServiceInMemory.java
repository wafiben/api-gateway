package dev.runner.apigatway.modules.category.service.category;

import dev.runner.apigatway.modules.category.dto.CategoryDto;
import dev.runner.apigatway.modules.category.dto.GetCategoryRequest;
import dev.runner.apigatway.modules.category.interfaces.CategoryServiceGateway;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceInMemory implements CategoryServiceGateway {

    private List<CategoryDto> categories = new ArrayList<>();

    public List<CategoryDto> categoryList(GetCategoryRequest requestDto) {
        return categories.stream().filter((elt) -> {
            return elt.getName().equals(requestDto.getName());
        }).toList();
    }

    public void saveCategory(CategoryDto category) {
        categories.add(category);
    }
}
