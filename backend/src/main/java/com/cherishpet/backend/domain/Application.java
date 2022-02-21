package com.cherishpet.backend.domain;

import com.cherishpet.backend.domain.post.Post;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Application {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private LocalDateTime applyDate;

    //==비즈니스 로직==//
    /**
     * 봉사 신청
     * */
    public static Application createApplication(Member member, Post post){
        Application application = Application.builder()
                .member(member)
                .post(post)
                .applyDate(LocalDateTime.now())
                .build();
        return application;
    }

}
