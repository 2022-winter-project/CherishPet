package com.cherishpet.backend.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotEmpty(message = "아이디를 입력해주세요")
    @Column(nullable = false)
    private String username;

    @NotEmpty(message = "비밀번호를 입력해주세요")
    @Column(nullable = false)
    private String password;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @Column(length = 1)
    private String sex;

    private int age;

    @NotEmpty(message = "번호를 입력해주세요")
    @Column(nullable = false)
    private String phoneNumber;

    private String personality;







}
