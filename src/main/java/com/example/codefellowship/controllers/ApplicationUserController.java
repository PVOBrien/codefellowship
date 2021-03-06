package com.example.codefellowship.controllers;

import com.example.codefellowship.models.user.ApplicationUser;
import com.example.codefellowship.models.user.ApplicationUserRepository;
import com.example.codefellowship.models.user.Post;
import org.aspectj.lang.annotation.Before;
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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ApplicationUserController {


    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup") // initially coming from /signup
    public ModelAndView makeNewUser(String username,
                                    String password,
                                    String firstName,
                                    String lastName,
                                    Date dateOfBirth,
                                    String bio,
                                    HttpServletRequest request) { //

    password = passwordEncoder.encode(password);

    ApplicationUser newUser = new ApplicationUser(username, password, firstName, lastName, dateOfBirth, bio); // dateOfBirth,
    applicationUserRepository.save(newUser);
    request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);

    return new ModelAndView("redirect:/login"); // now TEMPORARILY redirects login with this content, redirecting straight through /login,
    } // ^^^ https://www.baeldung.com/spring-redirect-and-forward ^^^

    @GetMapping("/user")
    public RedirectView sendItBackWithTheDetails(Model m, Principal principal) {

        System.out.println("Straight from user vanilla route" + principal.toString());
        return new RedirectView( "/user/" + principal.getName());

    }

    @PostMapping("/seeotheruser") // *anytime* you're sending something up, this is it.
    public RedirectView seethem(Model m, Principal principal, String theFollowed) {
        ApplicationUser personToSee = applicationUserRepository.findByUsername((theFollowed));

        return new RedirectView("/user/" + personToSee.getUsername() );
    }

    @GetMapping("/user/{username}")
    public String showUserForReal(Model m, @PathVariable String username, Principal principal){

        List<ApplicationUser> allUsers = applicationUserRepository.findAll(); // how to pass in something from a database to a list and on to the model.
        m.addAttribute("allusers", allUsers); // the one in quotes, is what it's called on the otherside. the var name is what is being passed in as a value to that key.

        m.addAttribute("principal", principal); // must be passed in explicitly.

        ApplicationUser thisUser = applicationUserRepository.findByUsername(username);
        m.addAttribute("userDeets", thisUser);

        return "user";
    }


    @PostMapping("/following")
    public RedirectView follow(Model m, Principal principal, String theFollowed) {
        ApplicationUser following = applicationUserRepository.findByUsername(principal.getName());
        ApplicationUser followed = applicationUserRepository.findByUsername(theFollowed);

        following.theFollowing.add(followed);
        followed.theFollowed.add(following);

//        following.theFollowed.add(followed);
//        followed.theFollowing.add(following);

        applicationUserRepository.save(following);
        applicationUserRepository.save(followed);

        m.addAttribute("principal", principal);

        return new RedirectView("/user");

    }

    @GetMapping("/feed")
    public String feed(Model m, Principal principal) {

        ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
        ArrayList<Long> idOfFollowed = new ArrayList<>();
        for (int i = 0; i < user.theFollowing.size(); i++) {
            idOfFollowed.add(user.theFollowing.get(i).getId());
        }
        ArrayList<Post> postsOfFollowed = new ArrayList<>();
        for (int i = 0; i < idOfFollowed.size(); i++) {
            ApplicationUser temp = applicationUserRepository.getOne(idOfFollowed.get(i));
            for (int j = 0; j < temp.posts.size(); j++) {
                postsOfFollowed.add(temp.posts.get(j));
            }
        }
        System.out.println(postsOfFollowed);
        m.addAttribute("postCollection", postsOfFollowed);

        return "feed";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {return "signup"; };

}
