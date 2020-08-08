package com.softserve.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Logout {
    @PostMapping("/perform-logout")
    public String home() {
        return "redirect:/logout";
    }
}