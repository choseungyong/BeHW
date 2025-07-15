package com.domain.app.option.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class OptionResponseDto {
    private Long id;
    private String name;
    private Integer quantity;
}
