package com.cherishpet.backend.service;

import com.cherishpet.backend.repository.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostServiceTest {

    @Autowired PostService postService;
    @Autowired PostRepository postRepository;

    @Test
    public void 게시물_등록() throws Exception{
        //given

        //when
        //then
    }

}
