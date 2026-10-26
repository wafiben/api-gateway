package dev.runner.apigatway.modules.product.interfaces;

import dev.runner.apigatway.modules.product.dto.ProductDto;
import java.util.List;

public interface ProductServiceGateway {

    List<ProductDto> listProducts();

    void saveProduct(ProductDto productDto);
}