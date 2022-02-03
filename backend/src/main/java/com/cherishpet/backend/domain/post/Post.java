package com.cherishpet.backend.domain.post;

import com.cherishpet.backend.domain.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Member;
import java.sql.Time;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 자식테이블 하나로 전부 합침
@NoArgsConstructor
@AllArgsConstructor
public abstract class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private User user_id; // 연관관계의 주인

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private Date volunteerDate;

    @Column(nullable = false)
    private Time volunteerTime;

    private int requiredNum;

    private int applicationNum;

    private String imageURL;

}
