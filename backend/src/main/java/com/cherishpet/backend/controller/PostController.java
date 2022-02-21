package com.cherishpet.backend.controller;

import com.cherishpet.backend.domain.post.Post;
import com.cherishpet.backend.dto.CreatePostDto;
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
    @PostMapping("/api/v1/posts")
    public Response createPost(@RequestBody @Valid CreatePostDto createPostDto){
        Long id = postService.savePost(createPostDto);
        Post post = postService.findOne(id);
        return new Response(201,true,"created post successfully", post);
    }

    // 게시물 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/v1/posts/{username}")
    public Response findPostByUsername(@PathVariable String username){
        List<Post> posts = postService.findPostByUsername(username);
        return new Response(200,true,"found post successfully", posts);
    }

}