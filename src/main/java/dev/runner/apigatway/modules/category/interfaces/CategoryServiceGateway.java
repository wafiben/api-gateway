package dev.runner.apigatway.modules.category.interfaces;

import dev.runner.apigatway.modules.category.dto.CategoryDto;
import dev.runner.apigatway.modules.category.dto.GetCategoryRequest;

import java.util.List;

public interface CategoryServiceGateway {
    List<CategoryDto> categoryList(GetCategoryRequest requestDto);

    void saveCategory(CategoryDto category);
}
