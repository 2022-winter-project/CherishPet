package com.cherishpet.backend.service;

import com.cherishpet.backend.domain.Member;
import com.cherishpet.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 로그인
    @Transactional
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    // 회원 정보 등록
    @Transactional
    public void updateMemberInfo(Long id,String name, String sex, int age, String phoneNumber, String personality){
        Member member = memberRepository.findOne(id);
        member.updateMember(name, sex, age, phoneNumber, personality); // 변경 감지
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
