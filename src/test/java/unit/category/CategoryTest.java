package unit.category;

import dev.runner.apigatway.modules.category.categorybuilder.CategoryBuilder;
import dev.runner.apigatway.modules.category.dto.CategoryDto;
import dev.runner.apigatway.modules.category.dto.GetCategoryRequest;
import dev.runner.apigatway.modules.category.interfaces.CategoryServiceGateway;
import dev.runner.apigatway.modules.category.service.category.CategoryServiceInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CategoryTest {
    private CategoryServiceGateway categoryService;

    @BeforeEach
    void setUp() {
        this.categoryService = new CategoryServiceInMemory();
    }

    @Test
    void should_filter_by_id_and_name() {
        // Arrange
        CategoryDto cat1 = new CategoryBuilder().id("1").name("Sport").build();
        CategoryDto cat2 = new CategoryBuilder().id("2").name("Music").build();
        categoryService.saveCategory(cat1);
        categoryService.saveCategory(cat2);

        // Act
        List<CategoryDto> result = categoryService.categoryList(new GetCategoryRequest("Sport", "dd"));

        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Sport");
    }

    @Test
    void should_return_empty_when_no_match() {
        // Arrange
        CategoryDto cat1 = new CategoryBuilder().id("1").name("Sport").build();
        categoryService.saveCategory(cat1);

        // Act
        List<CategoryDto> result = categoryService.categoryList(new GetCategoryRequest("99", "Unknown"));

        // Assert
        assertThat(result).isEmpty();
    }
}
