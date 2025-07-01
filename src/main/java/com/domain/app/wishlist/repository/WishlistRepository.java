package com.domain.app.wishlist.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.domain.app.wishlist.domain.Wishlist;

/**
 * WishRepository
 *  - 왜? 반복되는 JPA 쿼리 없이, 메서드명 기반으로 위시 조회·추가·삭제를 편하게 처리하기 위해.
 */
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findAllByMemberId(Long memberId);
    Optional<Wishlist> findByMemberIdAndProductId(Long memberId, Long productId);
    void deleteByMemberIdAndProductId(Long memberId, Long productId);
}
