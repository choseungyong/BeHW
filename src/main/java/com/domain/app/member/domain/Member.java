package com.domain.app.member.domain;

import com.domain.app.wishlist.domain.Wishlist;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wishlist> wishes = new ArrayList<>();

    public void addWish(Wishlist wishlist) {
        wishes.add(wishlist);
        wishlist.setMember(this);
    }

    public void removeWish(Wishlist wishlist) {
        wishes.remove(wishlist);
        wishlist.setMember(null);
    }
}
