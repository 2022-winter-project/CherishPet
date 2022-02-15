package com.cherishpet.backend.service;


import com.cherishpet.backend.domain.Member;
import com.cherishpet.backend.dto.CreateMemberDto;
import com.cherishpet.backend.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원정보_등록() throws Exception {
        //given
        final CreateMemberDto member1 = createMember(1);
        final CreateMemberDto member2 = createMember(2);
        //when
        Long saveId1 = memberService.join(member1);
        Long saveId2 = memberService.join(member2);
        //then
        assertEquals(member1.getUsername(),memberRepository.findOne(saveId1).getUsername());
        assertEquals(member2.getUsername(),memberRepository.findOne(saveId2).getUsername());
    }

    @Test
    public void 회원정보_조회() throws Exception {
        //given
        CreateMemberDto member1 = createMember(1);
        Long findId = memberService.join(member1);
        //when
        Member member2 = memberService.findMemberById(findId);
        //then
        assertEquals(findId,member2.getId());

    }

    public CreateMemberDto createMember(int i){
        return CreateMemberDto.builder()
                .username("test"+i)
                .name("홍길동"+i)
                .password("1q2w3e4r")
                .build();
    }
}