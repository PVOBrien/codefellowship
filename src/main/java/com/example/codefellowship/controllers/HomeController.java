package com.example.codefellowship.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
    @GetMapping("/")
    public String showHome(Model m, Principal principal) { // TODO: always include Model and Principal here on out.

        m.addAttribute("principal", principal);

        return "home";
    }

//    @GetMapping("/signup")
//    public String getSigned(){
//        return "signup";
//    }
}