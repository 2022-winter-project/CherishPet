package com.cherishpet.backend.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @NotEmpty(message = "아이디를 입력해주세요")
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    // **** 회원 추가 정보 ****

    private String name;

    @Column(length = 1)
    private String sex;

    private int age;

    private String phoneNumber;

    private String personality;

    /** 비즈니스 로직 **/
    // 회원 정보 등록
    public void updateMember(String name, String sex, int age, String phoneNumber, String personality){
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.personality = personality;
    }







}
