package com.cherishpet.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServlet;

@Controller
public class KakaoController {

    @GetMapping(value = "/api/login/getKakaoAuthUrl")
    public String getKakaoAuthUrl(HttpServlet request) throws Exception {
        return null;
    }
}
