package com.domain.app.wishlist.controller;

import com.domain.app.member.domain.Member;
import com.domain.app.security.login.LoginMember;
import com.domain.app.wishlist.dto.WishlistRequestDto;
import com.domain.app.wishlist.dto.WishlistResponseDto;
import com.domain.app.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishes")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @GetMapping(params = "!page")
    public List<WishlistResponseDto> listAll(
            @LoginMember Member member
    ) {
        return wishlistService.getWishes(member);
    }

    // 2) 페이지네이션용: page 파라미터가 있을 때
    @GetMapping
    public Page<WishlistResponseDto> listPage(
            @LoginMember Member member,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        return wishlistService.getWishes(member, pageable);
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
        wishlistService.removeWish(member, productId);
    }
}
