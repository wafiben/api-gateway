package dev.runner.apigatway.modules.category;

import dev.runner.apigatway.modules.category.dto.CategoryDto;
import dev.runner.apigatway.modules.category.dto.GetCategoryRequest;
import dev.runner.apigatway.modules.category.interfaces.CategoryServiceGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/categories")
public class CategoryGatewayController {

    private final CategoryServiceGateway categoryService;

    public CategoryGatewayController(CategoryServiceGateway categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<CategoryDto>> getCategories(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String name
    ) {
        return ResponseEntity.ok(categoryService.categoryList(new GetCategoryRequest(id, name)));
    }
}