package com.cherishpet.backend.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import javax.persistence.Column;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SearchResultDto {
    private String place;
    private String title;
    private int requiredNum; // 필요 인원 수
    private int applicationNum; // 신청한 인원 수

    @QueryProjection
    public SearchResultDto(String place, String title, int requiredNum, int applicationNum) {
        this.place = place;
        this.title = title;
        this.requiredNum = requiredNum;
        this.applicationNum = applicationNum;
    }
}
