package com.cherishpet.backend.controller;

import com.cherishpet.backend.domain.Member;
import com.cherishpet.backend.dto.CreateMemberDto;
import com.cherishpet.backend.dto.LoginDto;
import com.cherishpet.backend.dto.MemberInfoDto;
import com.cherishpet.backend.dto.TokenDto;
import com.cherishpet.backend.jwt.JwtFilter;
import com.cherishpet.backend.jwt.TokenProvider;
import com.cherishpet.backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    // 기본 회원가입
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/members")
    public Response createMember(@RequestBody @Valid CreateMemberDto request) throws Exception{
        Member member = Member.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .name(request.getName())
                .build();
        memberService.join(request);
        MemberInfoDto memberInfoDto = MemberInfoDto.ToDto(member);
        return new Response(201,true,"created member successfully", memberInfoDto);
    }

    // 로그인 시 권한 검증 및 인증정보가 담긴 토큰 생성 api
    @PostMapping("/api/v1/authenticate")
    public Response authorize(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new Response(201,true,"login member successfully", new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK));
    }

    // 자신의 정보 조회 (이력서 조회)
    @GetMapping("/api/v1/member")
    @PreAuthorize("hasAnyRole('USER','ADMIN')") // 두 권한 모두 호출 가능한 api
    public Response getMyUserInfo() {
        return new Response(200,true,"found member successfully", memberService.getMyUserWithAuthorities().get());
    }

    // 전체 회원 조회
    @GetMapping("/api/v1/members/all")
    @PreAuthorize("hasAnyRole('ADMIN')") // 관리자 권한만 호출 가능한 api
    public Response getAllMembers() throws Exception {
        List<Member> members = memberService.findMembers();
        List<MemberInfoDto> collect =  members.stream()
                .map(member -> new MemberInfoDto(member))
                .collect(Collectors.toList());
        return new Response(200,true,"found members successfully", collect);

    }

    // 특정 회원 조회 (by id)
    @GetMapping("/api/v1/members/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')") // 관리자 권한만 호출 가능한 api
    public Response getMemberById(@PathVariable("id") Long id) throws Exception {
        Member member = memberService.findMemberById(id);
        MemberInfoDto memberInfoDto = MemberInfoDto.builder()
                .member(member).build();
        return new Response(200,true,"found member successfully", memberInfoDto);
    }

    // 특정 회원 조회 (by username)
    @GetMapping("/api/v1/member/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')") // 관리자 권한만 호출 가능한 api
    public Response getUserInfo(@PathVariable String username) {
        return new Response(200,true,"found member successfully", memberService.getUserWithAuthorities(username).get());
    }

}
