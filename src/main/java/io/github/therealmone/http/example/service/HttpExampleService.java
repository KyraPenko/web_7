package io.github.therealmone.http.example.service;

import io.github.therealmone.http.example.dto.CreateProductRequest;
import io.github.therealmone.http.example.dto.ProductDto;
import io.github.therealmone.http.example.dto.UpdateProductRequest;

import java.util.List;
import java.util.UUID;

public interface HttpExampleService {

    List<ProductDto> getProducts();
    void updateProduct(UpdateProductRequest product);
    void deleteProduct(UUID id);
    UUID createProduct(CreateProductRequest product);
    ProductDto getProduct(UUID id);
}
