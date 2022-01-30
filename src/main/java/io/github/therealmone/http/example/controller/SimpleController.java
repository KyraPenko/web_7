package io.github.therealmone.http.example.controller;

import io.github.therealmone.http.example.dto.ProductDto;
import io.github.therealmone.http.example.dto.UpdateProductRequest;
import io.github.therealmone.http.example.dto.CreateProductRequest;

import io.github.therealmone.http.example.service.HttpExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SimpleController {

    private final HttpExampleService service;

    @GetMapping("/products")
    public List<ProductDto> getProducts() {
        return service.getProducts();
    }

    @GetMapping("/products/{id}")
    public ProductDto getProduct(@PathVariable("id") UUID id) {
        return service.getProduct(id);
    }

    @PostMapping("/products")
    public void createProduct(@RequestBody CreateProductRequest product) {
        service.createProduct(product);
    }

    @PutMapping("/products/{id}")
    public void updateProduct(@PathVariable("id") UpdateProductRequest id) {
        service.updateProduct(id);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") UUID id) {
        service.deleteProduct(id);
    }
}
