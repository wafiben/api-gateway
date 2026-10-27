package dev.runner.apigatway.modules.product.service;

import dev.runner.apigatway.modules.product.dto.ProductDto;
import dev.runner.apigatway.modules.product.interfaces.ProductServiceGateway;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceInMemory implements ProductServiceGateway {

    public List<ProductDto> products = new ArrayList<>();

    public void saveProduct(ProductDto product) {
        this.products.add(product);
    }

    public List<ProductDto> listProducts() {
        return products.stream().toList();
    }
}
