package com.cherishpet.backend.controller;

import com.cherishpet.backend.dto.CreatePostDto;
import com.cherishpet.backend.service.ApplicationService;
import com.cherishpet.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;
    private final PostService postService;

    @GetMapping("/api/v1/application")
    @PreAuthorize("hasAnyRole('USER')")
    public Response getMyApplications(){
        return new Response(200,true,"get applications successfully",applicationService.findMyApplications());
    }

    @PostMapping("/api/v1/application/{id}")
    @PreAuthorize("hasAnyRole('USER')")
    public Response apply(@PathVariable("id") Long id){
        return new Response(200,true,"create application successfully",applicationService.apply(id));
    }

    @DeleteMapping("/api/v1/application/{id}")
    @PreAuthorize("hasAnyRole('USER')")
    public Response removeApplication(@PathVariable("id") Long id){
        applicationService.removeApplication(id);
        return new Response(200,true,"delete application successfully",id);
    }
}
