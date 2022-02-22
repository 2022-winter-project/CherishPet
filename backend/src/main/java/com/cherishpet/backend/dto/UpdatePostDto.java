package com.cherishpet.backend.dto;

import com.cherishpet.backend.domain.Authority;
import com.cherishpet.backend.domain.Member;
import com.cherishpet.backend.domain.post.*;
import lombok.*;

import java.sql.Time;
import java.util.Collections;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class UpdatePostDto {
    private Long post_id;
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

    public static Post toEntity(UpdatePostDto updatePostDto) {
        Post post;
        switch (updatePostDto.getCategory()) {
            case "Facility":
                post = Facility.childBuilder()
                        .id(updatePostDto.getPost_id())
                        .title(updatePostDto.getTitle())
                        .place(updatePostDto.getPlace())
                        .region(updatePostDto.getRegion())
                        .phoneNumber(updatePostDto.getPhoneNum())
                        .volunteerDate(updatePostDto.getVolunteerDate())
                        .volunteerTime(updatePostDto.getVolunteerTime())
                        .requiredNum(updatePostDto.getRequiredNum())
                        .content(updatePostDto.getContent())
                        .imageURL(updatePostDto.getImageURL())
                        .build();
                break;
            case "Flight":
                post = Flight.childBuilder()
                        .id(updatePostDto.getPost_id())
                        .title(updatePostDto.getTitle())
                        .place(updatePostDto.getPlace())
                        .region(updatePostDto.getRegion())
                        .phoneNumber(updatePostDto.getPhoneNum())
                        .volunteerDate(updatePostDto.getVolunteerDate())
                        .volunteerTime(updatePostDto.getVolunteerTime())
                        .requiredNum(updatePostDto.getRequiredNum())
                        .content(updatePostDto.getContent())
                        .imageURL(updatePostDto.getImageURL())
                        .build();
                break;
            case "TemporaryCare":
                post = TemporaryCare.childBuilder()
                        .id(updatePostDto.getPost_id())
                        .title(updatePostDto.getTitle())
                        .place(updatePostDto.getPlace())
                        .region(updatePostDto.getRegion())
                        .phoneNumber(updatePostDto.getPhoneNum())
                        .volunteerDate(updatePostDto.getVolunteerDate())
                        .volunteerTime(updatePostDto.getVolunteerTime())
                        .requiredNum(updatePostDto.getRequiredNum())
                        .content(updatePostDto.getContent())
                        .imageURL(updatePostDto.getImageURL())
                        .build();
                break;
            default:
                post = Etc.childBuilder()
                        .id(updatePostDto.getPost_id())
                        .title(updatePostDto.getTitle())
                        .place(updatePostDto.getPlace())
                        .region(updatePostDto.getRegion())
                        .phoneNumber(updatePostDto.getPhoneNum())
                        .volunteerDate(updatePostDto.getVolunteerDate())
                        .volunteerTime(updatePostDto.getVolunteerTime())
                        .requiredNum(updatePostDto.getRequiredNum())
                        .content(updatePostDto.getContent())
                        .imageURL(updatePostDto.getImageURL())
                        .build();
                break;
        }
        return post;
    }
}
