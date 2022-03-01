package com.cherishpet.backend.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SearchResultDto {
    private String place;
    private String title;
    private String region;
    private int requiredNum; // 필요 인원 수
    private int applicationNum; // 신청한 인원 수
    private LocalDateTime postDate;

    @QueryProjection
    public SearchResultDto(String place, String title, String region, int requiredNum, int applicationNum, LocalDateTime postDate) {
        this.place = place;
        this.title = title;
        this.region = region;
        this.requiredNum = requiredNum;
        this.applicationNum = applicationNum;
        this.postDate = postDate;
    }
}
