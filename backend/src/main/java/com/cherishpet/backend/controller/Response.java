package com.cherishpet.backend.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T> {
    private int code;
    private Boolean success;
    private String message;
    private T result;
}