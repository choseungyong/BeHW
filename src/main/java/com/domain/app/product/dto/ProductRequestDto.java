package com.domain.app.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductRequestDto {

    @NotBlank(message = "상품 이름은 필수입니다.")
    @Size(max = 15, message = "상품 이름은 최대 15자까지 입력 가능합니다.")
    @Pattern(
            regexp = "^[a-zA-Z0-9가-힣\\s\\(\\)\\[\\]\\+\\-\\&/_]*$",
            message = "상품 이름에는 (), [], +, -, &, /, _ 외의 특수 문자를 사용할 수 없습니다."
    )
    private String name;

    private int price;

    private String imageUrl;

    private Long categoryId;
}
