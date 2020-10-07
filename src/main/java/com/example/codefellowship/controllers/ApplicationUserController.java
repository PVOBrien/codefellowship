package com.example.codefellowship.controllers;

import com.example.codefellowship.models.user.ApplicationUser;
import com.example.codefellowship.models.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal; // where we get the user deets.

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/newuser")
    public RedirectView makeNewUser(String userName,
                                    String password,
                                    String firstName,
                                    String lastName,
<<<<<<< HEAD
=======
//                                    String dateOfBirth,
>>>>>>> parent of 2e4018f... for kicks
                                    String bio) {

    password = passwordEncoder.encode(password);

    ApplicationUser newUser = new ApplicationUser(userName, password, firstName, lastName, bio); // dateOfBirth,

    applicationUserRepository.save(newUser);

    return new RedirectView("/"); // heads back to the home page
    }

    @GetMapping("/gotouserinfo")
    public String sendItBackWithTheDetails(Model m, Principal principal) {
        System.out.println(principal); // as of now returning null
        ApplicationUser thisUser = applicationUserRepository.findByUsername("PVOVideo");
        m.addAttribute("userDeets", thisUser);

        return "user.html";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/userinfo")
    public RedirectView gettingUser(String username) {

        return new RedirectView("/showuserinfo/ + ${username}");
    }
}
