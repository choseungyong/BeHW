package com.domain.app.wishlist.domain;

import com.domain.app.member.domain.Member;
import com.domain.app.product.domain.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wish")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Wishlist {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public void setMember(Member member) {
        this.member = member;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * 신규 Wishlist 엔티티 생성자
     *
     * @param member    찜을 등록한 회원 엔티티
     * @param product 찜한 상품의 ID
     */

    public Wishlist(Member member, Product product) {
        this.member = member;
        this.product = product;
    }

}
