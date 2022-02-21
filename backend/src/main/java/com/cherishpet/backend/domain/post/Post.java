package com.cherishpet.backend.domain.post;

import com.cherishpet.backend.domain.Member;
import com.cherishpet.backend.dto.CreatePostDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

import static com.cherishpet.backend.util.SecurityUtil.getCurrentUsername;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // single table 전략 사용
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DiscriminatorColumn(name = "DTYPE")
@Getter
public abstract class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member; // 연관관계의 주인

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

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date volunteerDate;

    private Time volunteerTime;

    private int requiredNum; // 필요 인원 수

    @Column(columnDefinition = "integer default 0")
    private int applicationNum; // 신청한 인원 수

    private String imageURL;

    @Enumerated(EnumType.STRING)
    private MatchingStatus matchingStatus;

    //==게시물 생성 메서드==//
    public static Post createPost(CreatePostDto createPostDto, Member member) {
        Post post;
        switch (createPostDto.getCategory()) {
            case "Facility":
                post = Facility.childBuilder()
                        .member(member)
                        .title(createPostDto.getTitle())
                        .place(createPostDto.getPlace())
                        .region(createPostDto.getRegion())
                        .phoneNumber(createPostDto.getPhoneNum())
                        .volunteerDate(createPostDto.getVolunteerDate())
                        .volunteerTime(createPostDto.getVolunteerTime())
                        .requiredNum(createPostDto.getRequiredNum())
                        .applicationNum(0)
                        .content(createPostDto.getContent())
                        .imageURL(createPostDto.getImageURL())
                        .matchingStatus(MatchingStatus.WAITING)
                        .build();
                break;
            case "Flight":
                post = Flight.childBuilder()
                        .member(member)
                        .title(createPostDto.getTitle())
                        .place(createPostDto.getPlace())
                        .region(createPostDto.getRegion())
                        .phoneNumber(createPostDto.getPhoneNum())
                        .volunteerDate(createPostDto.getVolunteerDate())
                        .volunteerTime(createPostDto.getVolunteerTime())
                        .requiredNum(createPostDto.getRequiredNum())
                        .applicationNum(0)
                        .content(createPostDto.getContent())
                        .imageURL(createPostDto.getImageURL())
                        .matchingStatus(MatchingStatus.WAITING)
                        .build();
                break;
            case "TemporaryCare":
                post = TemporaryCare.childBuilder()
                        .member(member)
                        .title(createPostDto.getTitle())
                        .place(createPostDto.getPlace())
                        .region(createPostDto.getRegion())
                        .phoneNumber(createPostDto.getPhoneNum())
                        .volunteerDate(createPostDto.getVolunteerDate())
                        .volunteerTime(createPostDto.getVolunteerTime())
                        .requiredNum(createPostDto.getRequiredNum())
                        .applicationNum(0)
                        .content(createPostDto.getContent())
                        .imageURL(createPostDto.getImageURL())
                        .matchingStatus(MatchingStatus.WAITING)
                        .build();
                break;
            default:
                post = Etc.childBuilder()
                        .member(member)
                        .title(createPostDto.getTitle())
                        .place(createPostDto.getPlace())
                        .region(createPostDto.getRegion())
                        .phoneNumber(createPostDto.getPhoneNum())
                        .volunteerDate(createPostDto.getVolunteerDate())
                        .volunteerTime(createPostDto.getVolunteerTime())
                        .requiredNum(createPostDto.getRequiredNum())
                        .applicationNum(0)
                        .content(createPostDto.getContent())
                        .imageURL(createPostDto.getImageURL())
                        .matchingStatus(MatchingStatus.WAITING)
                        .build();
                break;
        }
        return post;
    }

    //==게시물 수정 메서드==//
    @Builder
    public void updatePost(String region, String phoneNumber, Date volunteerDate, Time volunteerTime, String content){
        this.region = region;
        this.phoneNumber = phoneNumber;
        this.volunteerDate = volunteerDate;
        this.volunteerTime = volunteerTime;
        this.content = content;
    }

    // 신청인원 추가
    public void addApplicationNumber(){
        this.applicationNum+=1;
    }

    // 신청인원 취소
    public void subtractApplicationNumber(){
        this.applicationNum-=1;
    }

}
