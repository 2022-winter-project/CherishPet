package com.cherishpet.backend.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class PostSerachDto {
    private String region;
    private String keyword;
    private String sortType;
}
