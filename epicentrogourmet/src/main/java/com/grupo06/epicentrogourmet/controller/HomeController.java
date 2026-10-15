package com.grupo06.epicentrogourmet.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.grupo06.epicentrogourmet.entity.Festival;
import com.grupo06.epicentrogourmet.service.FestivalService;

@Controller
public class HomeController {

    private final FestivalService festivalService;

    public HomeController(FestivalService festivalService) {
        this.festivalService = festivalService;
    }

    @GetMapping("/")
    public String inicio(
            Model model,
            Authentication authentication) {

        List<Festival> festivales =
                festivalService.listarFestivales();

        model.addAttribute(
                "festivales",
                festivales
        );

        if (authentication != null) {

            model.addAttribute(
                    "username",
                    authentication.getName()
            );

            boolean esAdmin =
                    authentication
                            .getAuthorities()
                            .stream()
                            .anyMatch(authority ->
                                    authority
                                            .getAuthority()
                                            .equals(
                                                    "ROLE_ADMINISTRADOR"
                                            )
                            );

            model.addAttribute(
                    "esAdmin",
                    esAdmin
            );
        }

        return "index";
    }
}