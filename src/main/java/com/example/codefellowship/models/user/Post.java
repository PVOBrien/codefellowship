package com.example.codefellowship.models.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity // This is a database.
public class Post { // This is the name of the database.

    @Id // this is going to be it's UID. make sure it's from javax persistence because intelliJ of course wants to import the wrong one to begin with.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This is what'll get that ID.
    long id; // This is that id, declared.

    @ManyToOne // there'll be many POSTS to one USER.

    private ApplicationUser applicationUser; // might not be important?
    private String firstName;
    private String body; // where the post will reside.

    Timestamp createdAt = new Timestamp(System.currentTimeMillis()); // Timestamp of .sql type. Uses the System.current... to get it)

    public Post(){} // for Spring boot reasons. why? *why*?? BECAUSE.

    public Post(String body, String firstName){
        this.body = body;
        this.firstName = firstName;
        this.createdAt = new Timestamp(System.currentTimeMillis()); // TODO: yeah?
//        this.applicationUser = applicationUser; // TODO: we grab this someplace else?
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public ApplicationUser getApplicationUser() { return applicationUser; }
    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
