package com.domain.app.wishlist.repository;

import java.util.List;
import java.util.Optional;

import com.domain.app.member.domain.Member;
import com.domain.app.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.domain.app.wishlist.domain.Wishlist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Page<Wishlist> findAllByMember(Member member, Pageable pageable);

    Optional<Wishlist> findByMemberAndProduct(Member member, Product product);

    void deleteByMemberAndProduct(Member member, Product product);

    @Query("SELECT w FROM Wishlist w " +
            "JOIN FETCH w.product p " +
            "WHERE w.member = :member")
    List<Wishlist> findAllWithProductByMember(@Param("member") Member member);
}
