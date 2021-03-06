package com.cherishpet.backend.controller;

import com.cherishpet.backend.domain.post.Post;
import com.cherishpet.backend.dto.CreatePostDto;
import com.cherishpet.backend.dto.PostSerachDto;
import com.cherishpet.backend.dto.SearchResultDto;
import com.cherishpet.backend.dto.UpdatePostDto;
import com.cherishpet.backend.service.ApplicationService;
import com.cherishpet.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final ApplicationService applicationService;

    // 게시물 등록
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/post")
    public Response createPost(@RequestBody @Valid CreatePostDto createPostDto){
        Long id = postService.savePost(createPostDto);
        Post post = postService.findOne(id);
        return new Response(201,true,"created post successfully", post);
    }

    // 게시물 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/v1/post")
    public Response getPostByUsername(){
        List<Post> posts = postService.findPostByUsername();
        return new Response(200,true,"found post successfully", posts);
    }

    // 게시물 수정
    @PutMapping("/api/v1/post/{id}")
    public Response updatePost(@PathVariable Long id,
                               @RequestBody @Valid UpdatePostDto updatePostDto) {
        postService.updatePost(id, updatePostDto);
        Post post = postService.findOne(id);
        return new Response(200,true,"update post successfully", post);
    }

    // 게시물 삭제
    @DeleteMapping("/api/v1/post/{id}")
    public Response deletePost(@PathVariable Long id){
        postService.removePost(id);
        return new Response(200,true,"delete post successfully", id);
    }

    //게시물 조회
    @GetMapping("/api/v1/post/search")
    public Response searchPosts(@RequestParam(value="region") String region,
                                @RequestParam(value="keyword") String keyword,
                                @RequestParam(value="sorting") String sorting){
        PostSerachDto postSerachDto = PostSerachDto.builder()
                .region(region)
                .keyword(keyword)
                .sortType(sorting)
                .build();
        List<SearchResultDto> posts = postService.searchPost(postSerachDto);
        return new Response(200,true,"get posts successfully", posts);
    }
}
