package com.cherishpet.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedMemberException extends RuntimeException{

    public UnAuthorizedMemberException(String s) {

    }
}
