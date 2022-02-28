package com.cherishpet.backend.repository;

import com.cherishpet.backend.domain.Member;
import com.cherishpet.backend.domain.post.Post;
import com.cherishpet.backend.domain.post.QPost;
import com.cherishpet.backend.dto.PostSerachDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.cherishpet.backend.domain.post.QPost.post;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public void save(Post post){
        em.persist(post);
    }

    public Post findOne(Long id){
        return em.find(Post.class,id);
    }

    public List<Post> findPostByUsername(String username) {
        return em.createQuery("select p from Post p join fetch p.member where p.member.username=:username",Post.class)
                .setParameter("username",username)
                .getResultList();

    }

    public List<Post> findAll() {
        return em.createQuery("select m from Post m", Post.class)
                .getResultList();
    }

    public void remove(Post post){
        em.remove(post);
    }



}
