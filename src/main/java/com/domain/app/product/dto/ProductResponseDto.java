package com.domain.app.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
public class ProductResponseDto {

    private final long id;
    private final String name;
    private final int price;
    private final String imageUrl;
    private Long categoryId;

    @Builder
    public ProductResponseDto(Long id, String name, int price, String imageUrl, Long categoryId){
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
    }
}
