package com.example.codefellowship.controllers;

import com.example.codefellowship.models.user.ApplicationUser;
import com.example.codefellowship.models.user.ApplicationUserRepository;
import com.example.codefellowship.models.user.Post;
import com.example.codefellowship.models.user.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller // means it's going to have routes
public class PostController {

    @Autowired // there's going to be database work.
    ApplicationUserRepository applicationUserRepository; // here's that interface to *interface* with the db.

    @Autowired // 'nother one!
    PostRepository postRepository; // all done did up.

    @PostMapping("/savepost")
    public RedirectView makeNewPost(String body, long id){
        ApplicationUser user = applicationUserRepository.getOne(id);
        Post post = new Post(body);
        post.setApplicationUser(user);
            // ^^ this is all saving the vars in places

        postRepository.save(post); // post now save to the postRepo/db
        user.posts.add(post); // post now added/referenced(?) to the respective user
        applicationUserRepository.save(user); // now saving the updated user w the new deets

        return new RedirectView("/user/" + user.getUsername());
    }


}
