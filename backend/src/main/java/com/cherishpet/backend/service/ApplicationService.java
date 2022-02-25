package com.cherishpet.backend.service;

import com.cherishpet.backend.domain.Application;
import com.cherishpet.backend.domain.Member;
import com.cherishpet.backend.domain.post.Post;
import com.cherishpet.backend.repository.ApplicationRepository;
import com.cherishpet.backend.repository.MemberRepository;
import com.cherishpet.backend.repository.PostRepository;
import com.cherishpet.backend.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public Application findOne(Long id){
        return applicationRepository.findOne(id);
    }

    // username 으로 신청 내역 조회
    public List<Application> findMyApplications(){
        String username = SecurityUtil.getCurrentUsername().get();
        return applicationRepository.findApplicationsByUsername(username);
    }

    // 특정 게시글 봉사 신청
    @Transactional
    public Long apply(Long postId){
        //엔티티 조회
        String username = SecurityUtil.getCurrentUsername().get();
        Member member = memberRepository.findOneWithAuthoritiesByUsername(username).get();
        Post post = postRepository.findOne(postId);

        //신청자 수 늘리기
        post.addApplicationNumber();

        //봉사 신청서 생성
        Application application = Application.createApplication(member,post);

        //저장
        applicationRepository.save(application);

        return application.getId();
    }

    @Transactional
    public void removeApplication(Long applicationId){

        //엔티티 조회
        Application application = applicationRepository.findOne(applicationId);
        Post post = (Post) applicationRepository.findPostByApplicationId(applicationId).get();

        //신청자 수 내리기
        post.subtractApplicationNumber();

        //신청서 삭제
        applicationRepository.remove(application);

    }
}
