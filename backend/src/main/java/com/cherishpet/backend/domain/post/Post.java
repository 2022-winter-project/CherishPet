package com.cherishpet.backend.domain.post;

import com.cherishpet.backend.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // single table 전략 사용
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn(name = "DTYPE")
@Getter
public abstract class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member_id; // 연관관계의 주인

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

    public void updatePost(String region,String phoneNumber, Date volunteerDate, Time volunteerTime, String content){
        this.region = region;
        this.phoneNumber = phoneNumber;
        this.volunteerDate = volunteerDate;
        this.volunteerTime = volunteerTime;
        this.content = content;
    }
}
