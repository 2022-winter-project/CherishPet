package com.cherishpet.backend.dto;

import com.cherishpet.backend.domain.Authority;
import com.cherishpet.backend.domain.Member;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateMemberDto {

    private String username;
    private String password;
    private String name;

    public CreateMemberDto(Member member){
        this.username = member.getUsername();
        this.password = member.getPassword();
        this.name = member.getName();
    }
    
    public static Member toEntity(CreateMemberDto memberDto){
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        return Member.builder()
                .username(memberDto.getUsername())
                .password(memberDto.getPassword())
                .name(memberDto.getName())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();
    }
}
