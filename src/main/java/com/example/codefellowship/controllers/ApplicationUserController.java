package com.example.codefellowship.controllers;

import com.example.codefellowship.models.user.ApplicationUser;
import com.example.codefellowship.models.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

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
//                                    long dateOfBirth,
                                    String bio) {

    password = passwordEncoder.encode(password);

    ApplicationUser newUser = new ApplicationUser(userName, password, firstName, lastName, bio); // , dateOfBirth

    applicationUserRepository.save(newUser);

    return new RedirectView("/");
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/userinfo")
    public RedirectView gettingUser(String username) {

        return new RedirectView("/showuserinfo/ + ${username});
    }
}
