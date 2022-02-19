package com.cherishpet.backend.domain.post;

import com.cherishpet.backend.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.sql.Time;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
public class TemporaryCare extends Post{

    @Builder(builderMethodName = "childBuilder")
    public TemporaryCare(Long id, Member member, String place, String region, String phoneNumber, String title, String content, Date volunteerDate, Time volunteerTime, int requiredNum, int applicationNum, String imageURL, MatchingStatus matchingStatus) {
        super(id, member, place, region, phoneNumber, title, content, volunteerDate, volunteerTime, requiredNum, applicationNum, imageURL, matchingStatus);
    }
}
