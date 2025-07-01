package com.domain.app.wishlist.service;

import com.domain.app.member.domain.Member;
import com.domain.app.wishlist.domain.Wishlist;
import com.domain.app.wishlist.dto.WishlistRequestDto;
import com.domain.app.wishlist.dto.WishlistResponseDto;
import com.domain.app.wishlist.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public List<WishlistResponseDto> getWishes(Long memberId) {
        return wishlistRepository.findAllByMemberId(memberId).stream()
                .map(w -> new WishlistResponseDto(w.getProductId()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void addWish(Member member, WishlistRequestDto req) {
        boolean exists = wishlistRepository
                .findByMemberIdAndProductId(member.getId(), req.getProductId())
                .isPresent();
        if (!exists) {
            wishlistRepository.save(new Wishlist(member, req.getProductId()));
        }
    }

    @Transactional
    public void removeWish(Long memberId, Long productId) {
        wishlistRepository.deleteByMemberIdAndProductId(memberId, productId);
    }
}
