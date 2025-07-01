package com.domain.app.product.controller;

import com.domain.app.product.dto.ProductRequestDto;
import com.domain.app.product.dto.ProductResponseDto;
import com.domain.app.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productservice;

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll() {
        return ResponseEntity.ok(productservice.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getInfo(@PathVariable Long id){
        return ResponseEntity.ok(productservice.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(
            @Valid @RequestBody ProductRequestDto request,
            BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException(result.getFieldError().getDefaultMessage());
        }
        return ResponseEntity.ok(productservice.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDto request,
            BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException(result.getFieldError().getDefaultMessage());
        }
        return ResponseEntity.ok(productservice.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productservice.delete(id);
        return ResponseEntity.noContent().build();
    }
}
