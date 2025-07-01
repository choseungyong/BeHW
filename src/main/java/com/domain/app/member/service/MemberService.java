package com.domain.app.member.service;

import com.domain.app.member.domain.Member;
import com.domain.app.member.dto.MemberLoginRequestDto;
import com.domain.app.member.dto.MemberRegisterRequestDto;
import com.domain.app.member.dto.MemberResponseDto;
import com.domain.app.member.dto.TokenResponseDto;
import com.domain.app.member.repository.MemberRepository;
import com.domain.app.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // 기존 회원 조회
    public MemberResponseDto getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."));
        return new MemberResponseDto(member.getId(), member.getEmail());
    }


    public TokenResponseDto register(MemberRegisterRequestDto requestDto) {
        // 이메일 중복 검사
        if (memberRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 사용 중인 이메일입니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        // 회원 저장
        Member member = Member.builder()
                .email(requestDto.getEmail())
                .password(encodedPassword)
                .build();
        memberRepository.save(member);

        // 토큰 발급 후 응답
        String token = jwtTokenProvider.generateToken(member);
        return new TokenResponseDto(token);
    }

    public TokenResponseDto login(MemberLoginRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "이메일이 존재하지 않습니다."));

        // 비밀번호 검증
        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "비밀번호가 일치하지 않습니다.");
        }

        // 토큰 발급 후 응답
        String token = jwtTokenProvider.generateToken(member);
        return new TokenResponseDto(token);
    }

    /**
     * 토큰에서 memberId(subject) 추출
     * @param token JWT 토큰 문자열 ("Bearer " 제외)
     * @return 토큰에 담긴 회원 ID
     */
    public Long parseMemberIdFromToken(String token) {
        return jwtTokenProvider.getMemberId(token);
    }

    /**
     * Resolver에서 사용할 실제 Member 조회
     * @param memberId 파싱된 회원 ID
     * @return 조회된 Member 엔티티
     */
    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "회원이 존재하지 않습니다."));
    }
}
