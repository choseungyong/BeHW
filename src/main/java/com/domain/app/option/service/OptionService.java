package com.domain.app.option.service;

import com.domain.app.option.domain.Option;
import com.domain.app.option.dto.OptionRequestDto;
import com.domain.app.option.dto.OptionResponseDto;
import com.domain.app.option.repository.OptionRepository;
import com.domain.app.product.domain.Product;
import com.domain.app.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OptionService {

    private final OptionRepository optionRepository;
    private final ProductRepository productRepository;

    public List<OptionResponseDto> getOptions(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
        return optionRepository.findAllByProduct(product).stream()
                .map(o -> OptionResponseDto.builder()
                        .id(o.getId())
                        .name(o.getName())
                        .quantity(o.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public OptionResponseDto addOption(Long productId, OptionRequestDto req) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        // 중복 옵션명 체크
        if (optionRepository.findByProductAndName(product, req.getName()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 옵션명입니다.");
        }

        Option opt = Option.builder()
                .product(product)
                .name(req.getName())
                .quantity(req.getQuantity())
                .build();
        Option saved = optionRepository.save(opt);
        return OptionResponseDto.builder()
                .id(saved.getId())
                .name(saved.getName())
                .quantity(saved.getQuantity())
                .build();
    }

    @Transactional
    public void deleteOption(Long id) {
        optionRepository.deleteById(id);
    }
}