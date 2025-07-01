package com.domain.app.wishlist.repository;

import java.util.List;
import java.util.Optional;

import com.domain.app.member.domain.Member;
import com.domain.app.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import com.domain.app.wishlist.domain.Wishlist;

/**
 * WishRepository
 *  - 왜? 반복되는 JPA 쿼리 없이, 메서드명 기반으로 위시 조회·추가·삭제를 편하게 처리하기 위해.
 */
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findAllByMember(Member member);

    Optional<Wishlist> findByMemberAndProduct(Member member, Product product);

    void deleteByMemberAndProduct(Member member, Product product);
}
