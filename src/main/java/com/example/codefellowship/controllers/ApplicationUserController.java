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
import java.sql.Date;


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
                                    String bio) {

//        Date sqlDate = new java.sql.Date(dateOfBirth.getTime); TODO: FIX THIS.

//    dateOfBirth = dateOfBirth.substring(0,10);
//    java.util.Date dateUtilDate =

    password = passwordEncoder.encode(password);

    ApplicationUser newUser = new ApplicationUser(userName, password, firstName, lastName, bio); //  dateOfBirth,

//    ApplicationUser newUser = new ApplicationUser(userName,password,firstName,lastName,dateOfBirth,bio); // THIS IS STRICT. MUST COME IN AS ABOVE AS RedirectView makeNewUserConstructor above.

    applicationUserRepository.save(newUser);

    return new RedirectView("/"); // heads back to the home page
    }

    @GetMapping("/gotouserinfo")
    public String sendItBackWithTheDetails(Model m, Principal principal) {
        System.out.println(principal); // as of now returning null
        ApplicationUser thisUser = applicationUserRepository.findByUsername(principal.getName());
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
