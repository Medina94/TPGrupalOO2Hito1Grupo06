package com.grupo06.epicentrogourmet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.grupo06.epicentrogourmet.helpers.ViewRouteHelper.LOGIN;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String mostrarLogin() {
        return LOGIN;
    }
}