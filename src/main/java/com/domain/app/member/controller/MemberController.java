package com.domain.app.member.controller;

import com.domain.app.member.dto.MemberLoginRequestDto;
import com.domain.app.member.dto.MemberRegisterRequestDto;
import com.domain.app.member.dto.MemberResponseDto;
import com.domain.app.member.dto.TokenResponseDto;
import com.domain.app.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable Long id) {
        MemberResponseDto responseDto = memberService.getMemberById(id);
        return ResponseEntity.ok(responseDto);
    }

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<TokenResponseDto> register(@RequestBody MemberRegisterRequestDto requestDto) {
        TokenResponseDto tokenResponse = memberService.register(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(tokenResponse);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody MemberLoginRequestDto requestDto) {
        TokenResponseDto tokenResponse = memberService.login(requestDto);
        return ResponseEntity.ok(tokenResponse);
    }
}
