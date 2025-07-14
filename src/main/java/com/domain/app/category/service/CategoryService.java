package com.domain.app.category.service;

import com.domain.app.category.dto.CategoryResponseDto;
import com.domain.app.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryResponseDto> getAll() {
        return categoryRepository.findAll().stream()
                .map(c -> CategoryResponseDto.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .color(c.getColor())
                        .imageUrl(c.getImageUrl())
                        .description(c.getDescription())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
