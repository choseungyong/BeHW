package com.domain.app.option.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
public class OptionRequestDto {

    @NotBlank
    @Size(max = 50)
    @Pattern(regexp = "^[\\w \\(\\)\\[\\]\\+\\-&/]+$",
            message = "옵션명에 허용되지 않는 문자가 포함되어 있습니다.")
    private final String name;

    @NotNull
    @Min(1)
    @Max(99_999_999)
    private final Integer quantity;
}