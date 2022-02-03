package com.cherishpet.backend.domain.post;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
public class Flight extends Post {

}
