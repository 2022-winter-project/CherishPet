package com.cherishpet.backend.dto;

import com.cherishpet.backend.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberDto {
    private String username;
    private String email;

    public CreateMemberDto(Member member){
        this.username = member.getUsername();
        this.email = member.getEmail();
    }
}
