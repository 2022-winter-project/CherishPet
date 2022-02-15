package com.cherishpet.backend.repository;

import com.cherishpet.backend.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberInterface extends JpaRepository<Member,Long> {

//    //User 정보를 가져올때 권한 정보도 같이 가져옴
//    @EntityGraph(attributePaths = "authorities") // Eager 조회로 authorities 정보를 같이 가져오게 됨
//    Optional<Member> findOneWithAuthoritiesByUsername(String username);
}
