package com.example.codefellowship.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String showHome(){
        return "home";
    }

//    @GetMapping("/signup")
//    public String getSigned(){
//        return "signup";
//    }
}