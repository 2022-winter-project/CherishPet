package com.cherishpet.backend.service;

import com.cherishpet.backend.domain.Application;
import com.cherishpet.backend.domain.Member;
import com.cherishpet.backend.domain.post.Post;
import com.cherishpet.backend.dto.CreatePostDto;
import com.cherishpet.backend.dto.UpdatePostDto;
import com.cherishpet.backend.exception.CustomException;
import com.cherishpet.backend.exception.ErrorCode;
import com.cherishpet.backend.repository.ApplicationRepository;
import com.cherishpet.backend.repository.MemberRepository;
import com.cherishpet.backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.cherishpet.backend.util.SecurityUtil.getCurrentUsername;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final ApplicationRepository applicationRepository;

    public Post findOne(Long id){
        return postRepository.findOne(id);
    }

    // 내가 올린 게시글 조회
    public List<Post> findPostByUsername(String username){
        return postRepository.findPostByUsername(username);
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
        String username = getCurrentUsername().get();
        Member member = memberRepository.findOneWithAuthoritiesByUsername(username).get();

        //게시물 생성
        Post post = Post.createPost(createPostDto,member);
        postRepository.save(post);

        return post.getId();
    }

    // 게시물 수정
    @Transactional
    public void updatePost(Long id, UpdatePostDto updatePostDto) {
        Post post = postRepository.findOne(id);
        post.updatePost(updatePostDto);
        postRepository.save(post);
    }

    // 게시물 삭제
    @Transactional
    public void removePost(Long post_id){
        String username = getCurrentUsername().get();
        Post post = postRepository.findOne(post_id);
        if (!post.getMember().getUsername().equals(username)){
            throw new CustomException(ErrorCode.UNAUTHORIZED_MEMBER);
        }
        Optional applications = applicationRepository.findApplicationByPostId(post_id);
        if(!applications.isEmpty()){
            throw new IllegalStateException("신청한 봉사자가 존재합니다. 취소를 먼저 해주세요.");
        }
        postRepository.remove(post);
    }

}
