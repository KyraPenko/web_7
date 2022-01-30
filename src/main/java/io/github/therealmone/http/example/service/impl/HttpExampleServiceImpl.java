package io.github.therealmone.http.example.service.impl;

import io.github.therealmone.http.example.controller.SimpleController;
import io.github.therealmone.http.example.dto.CreateProductRequest;
import io.github.therealmone.http.example.dto.ProductDto;
import io.github.therealmone.http.example.entity.Product;
import io.github.therealmone.http.example.dto.UpdateProductRequest;
import io.github.therealmone.http.example.repository.ProductRepository;
import io.github.therealmone.http.example.service.HttpExampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.hateoas.Link;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class HttpExampleServiceImpl implements HttpExampleService {

    private final ProductRepository productRepository;

        @Override
    public List<ProductDto> getProducts() {
            log.info("Get products");
            return StreamSupport.stream(productRepository.findAll().spliterator(),
                            false)
                    .map(product -> ProductDto.builder()
                            .name(product.getName())
                            .id(product.getId())
                            .build()
                            .add(WebMvcLinkBuilder.linkTo(SimpleController.class).slash("products").slash(product.getId()).withSelfRel()))
                    .collect(Collectors.toList());
    }

    @Override
    public void updateProduct(UpdateProductRequest product) {
        log.info("Update resource: {}", product);
        productRepository.save(Product.builder()
                .id(product.getId())
                .name(product.getName())
                .build());
    }

    @Override
    public void deleteProduct(UUID id) {
        log.info("Delete resource: {}", id);
        productRepository.deleteById(id);
    }

    @Override
    public UUID createProduct(CreateProductRequest product) {
        log.info("Create resource: {}", product);

        final var resource  = Product.builder()
                .name(product.getName())
                .id(UUID.randomUUID())
                .build();
        productRepository.save(resource);

        return resource.getId();
    }

    @Override
    public ProductDto getProduct(UUID id) {
        log.info("Get product by id {}", id);

        final var product = productRepository.findById(id);

        if (product.isPresent()) {
            Link link = WebMvcLinkBuilder.linkTo(SimpleController.class).slash("products").slash(product.get().getId()).withSelfRel();
            return ProductDto.builder()
                    .name(product.get().getName())
                    .id(product.get().getId())
                    .build()
                    .add(link);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Resource not found");
        }
    }

}
