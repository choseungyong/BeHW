package com.domain.app.option.controller;

import com.domain.app.option.dto.OptionRequestDto;
import com.domain.app.option.dto.OptionResponseDto;
import com.domain.app.option.service.OptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/{productId}/options")
@RequiredArgsConstructor
public class OptionController {

    private final OptionService optionService;

    @GetMapping
    public ResponseEntity<List<OptionResponseDto>> list(
            @PathVariable Long productId
    ) {
        return ResponseEntity.ok(optionService.getOptions(productId));
    }

    @PostMapping
    public ResponseEntity<OptionResponseDto> create(
            @PathVariable Long productId,
            @Valid @RequestBody OptionRequestDto req
    ) {
        OptionResponseDto dto = optionService.addOption(productId, req);
        return ResponseEntity.status(201).body(dto);
    }

    @DeleteMapping("/{optionId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long optionId
    ) {
        optionService.deleteOption(optionId);
        return ResponseEntity.noContent().build();
    }
}
