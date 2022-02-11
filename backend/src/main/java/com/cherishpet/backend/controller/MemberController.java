package com.cherishpet.backend.controller;

import com.cherishpet.backend.domain.Member;
import com.cherishpet.backend.dto.CreateMemberDto;
import com.cherishpet.backend.dto.MemberInfoDto;
import com.cherishpet.backend.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 전체 회원 조회
    @GetMapping("/api/v1/members")
    public Response findAllMembers() throws Exception {
        List<Member> members = memberService.findMembers();
        List<MemberInfoDto> collect =  members.stream()
                .map(member -> new MemberInfoDto(member))
                .collect(Collectors.toList());
        return new Response(200,true,"found members successfully", collect);

    }

    // 특정 회원 조회 (by id)
    @GetMapping("/api/v1/members/{id}")
    public Response findMemberById(@PathVariable("id") Long id) throws Exception {
        Member member = memberService.findMemberById(id);
        MemberInfoDto memberInfoDto = MemberInfoDto.builder()
                .member(member).build();
        return new Response(200,true,"found member successfully", memberInfoDto);
    }
    // 회원가입
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/members")
    public Response createMember(@RequestBody @Valid CreateMemberDto request) throws Exception{
        Member member = Member.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .build();
        memberService.join(member);
        MemberInfoDto memberInfoDto = MemberInfoDto.ToDto(member);
        return new Response(201,true,"created member successfully", memberInfoDto);
    }

    @Getter
    @AllArgsConstructor
    static class Response<T> {
        private int code;
        private Boolean success;
        private String message;
        private T result;
    }
}
