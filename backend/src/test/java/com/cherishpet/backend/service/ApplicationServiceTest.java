package com.cherishpet.backend.service;

import com.cherishpet.backend.domain.Application;
import com.cherishpet.backend.domain.Member;
import com.cherishpet.backend.domain.post.Facility;
import com.cherishpet.backend.domain.post.Flight;
import com.cherishpet.backend.domain.post.Post;
import com.cherishpet.backend.dto.CreateMemberDto;
import com.cherishpet.backend.dto.CreatePostDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ApplicationServiceTest {

    @Autowired MemberService memberService;
    @Autowired PostService postService;
    @Autowired ApplicationService applicationService;

    @Test
    public void 신청한_봉사내역() throws Exception {
        //given
        CreateMemberDto member = createMember(1);
        CreatePostDto post = createPost(1);

        System.out.println("test!!!"+ post.getCategory());
        Long memberId = memberService.join(member);
        Long postId = postService.savePost(post);

        //when
        Long applicationId = applicationService.apply(postId);
        Application application = applicationService.findOne(applicationId);

        //then
        assertEquals(applicationId,application.getId());
    }

    public CreateMemberDto createMember(int i){
        return CreateMemberDto.builder()
                .username("test"+i)
                .name("홍길동"+i)
                .password("1q2w3e4r")
                .build();
    }
    public CreatePostDto createPost(int i) {
        CreatePostDto createPostDto = CreatePostDto.builder()
                .title("aa")
                .place("aa")
                .region("aa")
                .phoneNum("1234")
                .requiredNum(3)
                .content("aaa")
                .imageURL("aaa")
                .category("Facility")
                .build();

        return createPostDto;
    }
}