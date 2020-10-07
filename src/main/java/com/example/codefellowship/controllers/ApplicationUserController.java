package com.example.codefellowship.controllers;

import com.example.codefellowship.models.user.ApplicationUser;
import com.example.codefellowship.models.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal; // where we get the user deets.

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup") // initially coming from /signup
    public RedirectView makeNewUser(String username,
                                    String password,
                                    String firstName,
                                    String lastName,
//                                    String dateOfBirth,
                                    String bio) { // HttpServletRequest request

    password = passwordEncoder.encode(password);

    ApplicationUser newUser = new ApplicationUser(username, password, firstName, lastName, bio); // dateOfBirth,

    applicationUserRepository.save(newUser);
//    request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);

    return new RedirectView("/login"); // heads back to the home page
    }

    @GetMapping("/user")
    public RedirectView sendItBackWithTheDetails(Principal principal) {
//        System.out.println(principal); // as of now returning null
//        ApplicationUser thisUser = applicationUserRepository.findByUsername(principal.getName());
//        m.addAttribute("userDeets", thisUser);

        return new RedirectView( "/user/" + principal.getName());
    }

    @GetMapping("/user/{username}")
    public String showUserForReal(Model m, @PathVariable String username, Principal principal){
        System.out.println(principal); // as of now returning null
        ApplicationUser thisUser = applicationUserRepository.findByUsername(principal.getName());
        m.addAttribute("userDeets", thisUser);

        return "user";

    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {return "signup"; };

}
