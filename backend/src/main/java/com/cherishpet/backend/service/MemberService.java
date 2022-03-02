package com.cherishpet.backend.service;

import com.cherishpet.backend.domain.Authority;
import com.cherishpet.backend.domain.Member;
import com.cherishpet.backend.dto.CreateMemberDto;
import com.cherishpet.backend.dto.MemberInfoDto;
import com.cherishpet.backend.exception.CustomException;
import com.cherishpet.backend.exception.ErrorCode;
import com.cherishpet.backend.repository.MemberRepository;
import com.cherishpet.backend.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    // ROLE_USER 권한 부여됨
    @Transactional
    public Long join(CreateMemberDto memberDto) {
        if (memberRepository.findOneWithAuthoritiesByUsername(memberDto.getUsername()).orElse(null) != null)  {
            throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
        }
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member = Member.builder()
                .username(memberDto.getUsername())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .name(memberDto.getName())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();
        memberRepository.save(member);

        return member.getId();
    }

    /**
     * 아래 두가지 메소드의 허용권한을 다르게 해서 권한검증에 대한 부분 테스트
     */

    public Optional<Member> getUserWithAuthorities(String username) {
        Optional<Member> result = memberRepository.findOneWithAuthoritiesByUsername(username);
        if (result.isEmpty()){
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
        }
        else{
            return result;
        }
    }

    public Optional<Member> getMyUserWithAuthorities() {
        Optional<Member> result = SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneWithAuthoritiesByUsername);
         if (result.isEmpty()){
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
         }
         else {
             return result;
         }
    }

    // 회원 정보 등록
    @Transactional
    public void updateMemberInfo(MemberInfoDto memberInfoDto){
        String username = SecurityUtil.getCurrentUsername().get();
        Member member = memberRepository.findOneWithAuthoritiesByUsername(username).get();
        member.updateMember(memberInfoDto.getName(), memberInfoDto.getSex(), memberInfoDto.getAge(), memberInfoDto.getPhoneNumber(), memberInfoDto.getPersonality()); // 변경 감지
    }

    // 회원 정보 조회
    public Member findMemberById(Long id){
        return memberRepository.findOne(id);
    }

    // 전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
}
