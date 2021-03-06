package com.cherishpet.backend.dto;

import com.cherishpet.backend.domain.post.*;
import lombok.*;

import java.lang.management.LockInfo;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CreatePostDto {

    private String title;
    private String place;
    private String region;
    private String phoneNum;
    private Date volunteerDate;
    private Time volunteerTime;
    private int requiredNum; // 필요 인원 수
    private String content;
    private String imageURL;
    private String category; // Flight, Facility, TemporaryCare, Etc

    public static Post toEntity(CreatePostDto createPostDto) {
        Post post;
        switch (createPostDto.getCategory()) {
            case "Facility":
                post = Facility.childBuilder()
                        .title(createPostDto.getTitle())
                        .place(createPostDto.getPlace())
                        .region(createPostDto.getRegion())
                        .phoneNumber(createPostDto.getPhoneNum())
                        .volunteerDate(createPostDto.getVolunteerDate())
                        .volunteerTime(createPostDto.getVolunteerTime())
                        .requiredNum(createPostDto.getRequiredNum())
                        .content(createPostDto.getContent())
                        .imageURL(createPostDto.getImageURL())
                        .build();
                break;
            case "Flight":
                post = Flight.childBuilder()
                        .title(createPostDto.getTitle())
                        .place(createPostDto.getPlace())
                        .region(createPostDto.getRegion())
                        .phoneNumber(createPostDto.getPhoneNum())
                        .volunteerDate(createPostDto.getVolunteerDate())
                        .volunteerTime(createPostDto.getVolunteerTime())
                        .requiredNum(createPostDto.getRequiredNum())
                        .content(createPostDto.getContent())
                        .imageURL(createPostDto.getImageURL())
                        .build();
                break;
            case "TemporaryCare":
                post = TemporaryCare.childBuilder()
                        .title(createPostDto.getTitle())
                        .place(createPostDto.getPlace())
                        .region(createPostDto.getRegion())
                        .phoneNumber(createPostDto.getPhoneNum())
                        .volunteerDate(createPostDto.getVolunteerDate())
                        .volunteerTime(createPostDto.getVolunteerTime())
                        .requiredNum(createPostDto.getRequiredNum())
                        .content(createPostDto.getContent())
                        .imageURL(createPostDto.getImageURL())
                        .build();
                break;
            default:
                post = Etc.childBuilder()
                        .title(createPostDto.getTitle())
                        .place(createPostDto.getPlace())
                        .region(createPostDto.getRegion())
                        .phoneNumber(createPostDto.getPhoneNum())
                        .volunteerDate(createPostDto.getVolunteerDate())
                        .volunteerTime(createPostDto.getVolunteerTime())
                        .requiredNum(createPostDto.getRequiredNum())
                        .content(createPostDto.getContent())
                        .imageURL(createPostDto.getImageURL())
                        .build();
                break;
        }
        return post;
    }
}
