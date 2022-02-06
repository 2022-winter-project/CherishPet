package com.cherishpet.backend.domain.post;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Builder
public class Flight extends Post {

}
