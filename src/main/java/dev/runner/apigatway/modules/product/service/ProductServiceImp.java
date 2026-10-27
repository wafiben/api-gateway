package dev.runner.apigatway.modules.product.service;

import dev.runner.apigatway.modules.product.dto.ProductDto;
import dev.runner.apigatway.modules.product.interfaces.ProductServiceGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImp implements ProductServiceGateway {

    private final RestTemplate restTemplate;
    private final String productServiceUrl;

    public ProductServiceImp(
            @Qualifier("plainRestTemplate") RestTemplate restTemplate,
            @Value("${services.products}") String productServiceUrl
    ) {
        this.restTemplate = restTemplate;
        this.productServiceUrl = productServiceUrl;
    }

    @Override
    public List<ProductDto> listProducts() {
        String url = UriComponentsBuilder
                .fromHttpUrl(productServiceUrl)
                .toUriString();

        URI uri = URI.create(url);

        ResponseEntity<ProductDto[]> response = restTemplate.getForEntity(uri, ProductDto[].class);

        ProductDto[] body = response.getBody();

        return body != null ? Arrays.asList(body) : List.of();
    }

    @Override
    public void saveProduct(ProductDto productDto) {
        System.out.println("HELLO");
    }
}