package com.example.codefellowship.models.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);
}
