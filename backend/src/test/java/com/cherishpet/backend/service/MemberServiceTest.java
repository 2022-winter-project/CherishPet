package com.cherishpet.backend.service;

import com.cherishpet.backend.domain.Member;
import com.cherishpet.backend.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원정보_등록() throws Exception {
        //given
        final Member member = createMember();
        //when
        Long saveId = memberService.join(member);
        //then
        assertEquals("회원 등록 실패",member.getId(),memberRepository.findOne(saveId).getId());
    }

    @Test
    public void 회원정보_조회() throws Exception {
        //given
        Member member1 = createMember();
        Long findId = memberService.join(member1);
        //when
        Member member2 = memberService.findMemberInfo(findId);
        //then
        assertEquals("회원 정보 조회 실패",findId,member1.getId());

    }

    public Member createMember(){
        return Member.builder()
                .username("test")
                .name("홍길동")
                .sex("M")
                .age(30)
                .phoneNumber("010-xxxx-xxxx")
                .personality("정의로움").build();
    }
}