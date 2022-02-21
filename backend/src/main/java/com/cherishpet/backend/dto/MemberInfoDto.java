package com.cherishpet.backend.dto;

import com.cherishpet.backend.domain.Member;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberInfoDto {
    private Long id;
    private String name;
    private String sex;
    private int age;
    private String phoneNumber;
    private String personality;

    @Builder
    public MemberInfoDto(Member member){
        this.id = member.getId();
        this.name = member.getName();
        this.sex = member.getSex();
        this.age = member.getAge();
        this.phoneNumber = member.getPhoneNumber();
        this.personality = member.getPersonality();
    }

    public static MemberInfoDto ToDto(Member member){
        return MemberInfoDto.builder()
                .member(member)
                .build();
    }
}
