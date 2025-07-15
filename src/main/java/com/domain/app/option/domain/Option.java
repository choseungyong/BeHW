package com.domain.app.option.domain;


import com.domain.app.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "options")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * 재고를 차감합니다.
     * @param amount 차감할 수량 (1 이상, 1e8 미만)
     * @throws IllegalArgumentException 잘못된 amount 이거나 재고 부족 시
     */
    public void subtract(int amount) {
        if (amount < 1 || amount >= 100_000_000) {
            throw new IllegalArgumentException("차감할 수량은 1 이상, 1억 미만이어야 합니다.");
        }
        if (this.quantity < amount) {
            throw new IllegalArgumentException("재고가 부족합니다. 현재 재고=" + this.quantity);
        }
        this.quantity -= amount;
    }
}

