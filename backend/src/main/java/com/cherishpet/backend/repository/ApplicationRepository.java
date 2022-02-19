package com.cherishpet.backend.repository;

import com.cherishpet.backend.domain.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
                                        "join fetch a.member where a.member.username =:username", Application.class)
                .setParameter("username",username)
                .getResultList();
    }
}
