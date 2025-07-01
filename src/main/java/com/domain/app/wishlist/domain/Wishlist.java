package com.domain.app.wishlist.domain;

import com.domain.app.member.domain.Member;
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

    @Column(name = "product_id", nullable = false)
    private Long productId;

    /**
     * 신규 Wishlist 엔티티 생성자
     *
     * @param member    찜을 등록한 회원 엔티티
     * @param productId 찜한 상품의 ID
     */

    public Wishlist(Member member, Long productId) {
        this.member = member;
        this.productId = productId;
    }

}
