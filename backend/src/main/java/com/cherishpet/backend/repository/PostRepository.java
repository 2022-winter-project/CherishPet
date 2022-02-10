package com.cherishpet.backend.repository;

import com.cherishpet.backend.domain.Member;
import com.cherishpet.backend.domain.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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

    public List<Post> findAll() {
        return em.createQuery("select m from Post m", Post.class)
                .getResultList();
    }
}
