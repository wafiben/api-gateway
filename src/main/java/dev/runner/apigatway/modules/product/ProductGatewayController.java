package dev.runner.apigatway.modules.product;


import dev.runner.apigatway.config.Endpoints;
import dev.runner.apigatway.modules.product.dto.ProductDto;
import dev.runner.apigatway.modules.product.interfaces.ProductServiceGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Endpoints.PRODUCT)
public class ProductGatewayController {

    private final ProductServiceGateway productService;

    public ProductGatewayController(ProductServiceGateway productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.ok(productService.listProducts());
    }
}
