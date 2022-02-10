package com.cherishpet.backend.service;

import com.cherishpet.backend.domain.post.Post;
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

    private final PostRepository postRepository;

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
}
