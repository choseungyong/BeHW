package com.domain.app.wishlist.service;

import com.domain.app.member.domain.Member;
import com.domain.app.product.domain.Product;
import com.domain.app.product.repository.ProductRepository;
import com.domain.app.wishlist.domain.Wishlist;
import com.domain.app.wishlist.dto.WishlistRequestDto;
import com.domain.app.wishlist.dto.WishlistResponseDto;
import com.domain.app.wishlist.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;

    // 기존 Page 반환 메서드
    public Page<WishlistResponseDto> getWishes(Member member, Pageable pageable) {
        return wishlistRepository.findAllByMember(member, pageable)
                .map(w -> new WishlistResponseDto(w.getProduct().getId()));
    }

    // **테스트 호환용** List 반환 메서드 추가
    public List<WishlistResponseDto> getWishes(Member member) {
        return getWishes(member, Pageable.unpaged())
                .getContent();
    }

    @Transactional
    public void addWish(Member member, WishlistRequestDto req) {
        // 1) 상품 엔티티 조회
        Product product = productRepository.findById(req.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        // 2) 중복 체크
        boolean exists = wishlistRepository
                .findByMemberAndProduct(member, product)
                .isPresent();
        if (exists) {
            return; // 혹은 예외 던지기
        }

        // 3) 연관관계로 위시 생성
        Wishlist wish = new Wishlist(member, product);
        wishlistRepository.save(wish);
    }

    @Transactional
    public void removeWish(Member member, Long productId) {
        // 1) 상품 엔티티 조회
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        // 2) 삭제
        wishlistRepository.deleteByMemberAndProduct(member, product);
    }
}
