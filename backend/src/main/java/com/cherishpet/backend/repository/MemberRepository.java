package com.cherishpet.backend.repository;

import com.cherishpet.backend.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository  {

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // username 기준으로 User 정보 가져옴
    @EntityGraph(attributePaths = "authorities") // Eager 조회로 authorities 정보를 같이 가져오게 됨
   public Optional<Member> findOneWithAuthoritiesByUsername(String username){
        List<Member> result = em.createQuery("select m from Member m where m.username=:username", Member.class)
                .setParameter("username", username)
                .getResultList();
        if (result.isEmpty()){
            return Optional.ofNullable(null);
        }
        else{
            return Optional.ofNullable(result.get(0));
        }
    }

}
