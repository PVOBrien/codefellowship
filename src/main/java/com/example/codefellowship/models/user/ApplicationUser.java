package com.example.codefellowship.models.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    String username; // is potato
    String password; // is potato
    String firstName;
    String lastName;
    Date dateOfBirth;
    String bio;

    public ApplicationUser(){};

    public ApplicationUser(String username, String password, String firstName, String lastName, Date dateOfBirth, String bio) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @OneToMany(mappedBy = "applicationUser", cascade = CascadeType.ALL) // connects this up to the posts...
    public List<Post> posts = new ArrayList<Post>(); // needs this to store the posts w user, link it all up.

    public List<Post> getPosts() { return posts; }

    public void setPost(List<Post> post) { this.posts = post; }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getUsername() { return username; }

    @Override
    public boolean isAccountNonExpired() { return true; } // TODO: Make sure these are true, else they are dead accounts.

    @Override
    public boolean isAccountNonLocked() {
        return true;
    } // TODO: Make sure these are true, else they are dead accounts.

    @Override
    public boolean isCredentialsNonExpired() { return true; } // TODO: Make sure these are true, else they are dead accounts.

    @Override
    public boolean isEnabled() {
        return true;
    } // TODO: Make sure these are true, else they are dead accounts.

    public void setUsername(String username) { this.username = username; }


    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
}
