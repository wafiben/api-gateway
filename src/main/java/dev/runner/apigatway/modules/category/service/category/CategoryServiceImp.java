package dev.runner.apigatway.modules.category.service.category;

import dev.runner.apigatway.modules.category.dto.CategoryDto;
import dev.runner.apigatway.modules.category.dto.GetCategoryRequest;
import dev.runner.apigatway.modules.category.interfaces.CategoryServiceGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryServiceGateway {

    private final RestTemplate restTemplate;
    private final String categoryServiceUrl;

    public CategoryServiceImp(@Qualifier("plainRestTemplate") RestTemplate restTemplate,
                              @Value("${services.categories}") String categoryServiceUrl) {
        this.restTemplate = restTemplate;
        this.categoryServiceUrl = categoryServiceUrl;
    }

    public List<CategoryDto> categoryList(GetCategoryRequest requestDto) {
        String url = UriComponentsBuilder.fromHttpUrl(categoryServiceUrl)
                .queryParamIfPresent("name", Optional.ofNullable(requestDto.getName()))
                .queryParamIfPresent("description", Optional.ofNullable(requestDto.getDescription()))
                .toUriString();

        try {
            ResponseEntity<CategoryDto[]> response = restTemplate.getForEntity(url, CategoryDto[].class);
            CategoryDto[] body = response.getBody();
            return body != null ? Arrays.asList(body) : List.of();
        } catch (HttpStatusCodeException e) {
            throw e;
        }
    }

    @Override
    public void saveCategory(CategoryDto category) {

    }
}