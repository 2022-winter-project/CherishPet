package com.cherishpet.backend.domain.post;

import com.cherishpet.backend.domain.Member;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Flight extends Post {

    @Builder(builderMethodName = "childBuilder")
    public Flight(Long id, Member member, String place, String region, String phoneNumber, String title, String content, Date volunteerDate, Time volunteerTime, int requiredNum, int applicationNum, String imageURL, MatchingStatus matchingStatus, LocalDateTime postDate) {
        super(id, member, place, region, phoneNumber, title, content, volunteerDate, volunteerTime, requiredNum, applicationNum, imageURL, matchingStatus, postDate);
    }
}
