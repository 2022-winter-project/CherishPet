package com.cherishpet.backend.repository;

import com.cherishpet.backend.domain.Application;
import com.cherishpet.backend.domain.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ApplicationRepository {

    private final EntityManager em;

    public void save(Application application){
        em.persist(application);
    }

    public Application findOne(Long id){
        return em.find(Application.class, id);
    }

    public List<Application> findApplicationsByUsername(String username){
        return
                em.createQuery("select a " +
                                        "from Application a " +
                                        "join fetch a.member " +
                                        "where a.member.username =:username", Application.class)
                .setParameter("username",username)
                .getResultList();
    }

    public Optional findApplicationByPostId(Long postId) {
        List<Application> result = em.createQuery("select a " +
                                "from Application a " +
                                "join fetch a.post " +
                                "where a.post.id=:postId", Application.class)
                                .setParameter("postId", postId)
                                .getResultList();

        if (result.isEmpty()){
            return Optional.empty();
        }
        else{
            return Optional.ofNullable(result.get(0));
        }
    }

    public void remove(Application application){
        em.remove(application);
    }

    public Optional findPostByApplicationId(Long applicationId) {
        Application application = findOne(applicationId);
        return Optional.ofNullable(application.getPost());
    }
}
