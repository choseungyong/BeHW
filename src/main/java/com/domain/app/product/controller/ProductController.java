package com.domain.app.product.controller;

import com.domain.app.product.dto.ProductRequestDto;
import com.domain.app.product.dto.ProductResponseDto;
import com.domain.app.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productservice;

    @GetMapping(params = "!page")
    public ResponseEntity<List<ProductResponseDto>> getAll() {
        List<ProductResponseDto> list = productservice.getAll();
        return ResponseEntity.ok(list);
    }

    /**
     * 페이징 조회 (page 파라미터가 있을 때)
     * GET /api/products?page=0&size=20&sort=name,asc
     */
    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>> list(
            @PageableDefault(size = 20, sort = "name", direction = Sort.Direction.ASC)
            Pageable pageable
    ) {
        Page<ProductResponseDto> page = productservice.getPage(pageable);
        return ResponseEntity.ok(page);
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
