package com.domain.app.product.service;

import com.domain.app.category.domain.Category;
import com.domain.app.category.repository.CategoryRepository;
import com.domain.app.product.domain.Product;
import com.domain.app.product.dto.ProductRequestDto;
import com.domain.app.product.dto.ProductResponseDto;
import com.domain.app.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductResponseDto> getPage(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(this::toResponseDto);
    }

    public List<ProductResponseDto> getAll() {
        return productRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public ProductResponseDto findById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
        return toResponseDto(product);
    }

    @Transactional
    public ProductResponseDto save(ProductRequestDto requestDto){
        validateName(requestDto.getName());

        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "존재하지 않는 카테고리입니다."
                ));

        Product product = Product.builder()
                .name(requestDto.getName())
                .price(requestDto.getPrice())
                .imageUrl(requestDto.getImageUrl())
                .category(category)
                .build();

        Product saved = productRepository.save(product);
        return toResponseDto(saved);
    }

    public ProductResponseDto update(Long id, ProductRequestDto requestDto){
        validateName(requestDto.getName());

        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "존재하지 않는 카테고리입니다."
                ));

        Product product = Product.builder()
                .id(id)
                .name(requestDto.getName())
                .price(requestDto.getPrice())
                .imageUrl(requestDto.getImageUrl())
                .category(category)
                .build();

        Product updated = productRepository.save(product);
        return toResponseDto(updated);
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }

    private void validateName(String name) {
        if (name.contains("카카오")) {
            throw new IllegalArgumentException("상품 이름에 '카카오'는 사용할 수 없습니다.");
        }
    }

    private ProductResponseDto toResponseDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .categoryId(product.getCategory().getId())
                .build();
    }
}
