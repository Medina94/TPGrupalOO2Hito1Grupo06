package com.grupo06.epicentrogourmet.controller;

import java.security.Principal;
import java.util.List;

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
    public String inicio(Model model, Principal principal) {

        List<Festival> festivales =
                festivalService.listarFestivales();

        model.addAttribute("festivales", festivales);

        if (principal != null) {
            model.addAttribute(
                    "username",
                    principal.getName()
            );
        }

        return "index";
    }
}