package com.domain.app.wishlist.repository;

import java.util.List;
import java.util.Optional;

import com.domain.app.member.domain.Member;
import com.domain.app.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.domain.app.wishlist.domain.Wishlist;


public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Page<Wishlist> findAllByMember(Member member, Pageable pageable);

    Optional<Wishlist> findByMemberAndProduct(Member member, Product product);

    void deleteByMemberAndProduct(Member member, Product product);
}
