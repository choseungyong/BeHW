package com.domain.app.wishlist.controller;

import com.domain.app.member.domain.Member;
import com.domain.app.security.login.LoginMember;
import com.domain.app.wishlist.dto.WishlistRequestDto;
import com.domain.app.wishlist.dto.WishlistResponseDto;
import com.domain.app.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishes")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @GetMapping
    public List<WishlistResponseDto> list(@LoginMember Member member) {
        return wishlistService.getWishes(member.getId());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody WishlistRequestDto requestDto,
                    @LoginMember Member member) {
        wishlistService.addWish(member, requestDto);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long productId,
                       @LoginMember Member member) {
        wishlistService.removeWish(member.getId(), productId);
    }
}
