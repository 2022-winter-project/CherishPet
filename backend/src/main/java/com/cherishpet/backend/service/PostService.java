package com.cherishpet.backend.service;

import com.cherishpet.backend.domain.Application;
import com.cherishpet.backend.domain.Member;
import com.cherishpet.backend.domain.post.Post;
import com.cherishpet.backend.dto.CreatePostDto;
import com.cherishpet.backend.repository.ApplicationRepository;
import com.cherishpet.backend.repository.MemberRepository;
import com.cherishpet.backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final ApplicationRepository applicationRepository;

    // 내가 올린 게시글 조회
    public Post findMyPost(Long id){
        return postRepository.findOne(id);
    }

/**
 * QueryDSL 사용해서 조건 검색에 둘다 포함해서 함수 만들기 
 * */

    // 전체 게시글 조회
    public List<Post> findAllPost(){
        return postRepository.findAll();
    }

    // 게시글 등록
    @Transactional
    public Long savePost(CreatePostDto createPostDto){
        Post post = CreatePostDto.toEntity(createPostDto);
        postRepository.save(post);

        return post.getId();
    }

    // 특정 게시글 봉사 신청
    @Transactional
    public Long apply(Long memberId, Long postId){

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Post post = postRepository.findOne(postId);

        //봉사 신청서 생성
        Application application = Application.createApplication(member,post);

        //저장
        applicationRepository.save(application);

        return application.getId();
    }
}
