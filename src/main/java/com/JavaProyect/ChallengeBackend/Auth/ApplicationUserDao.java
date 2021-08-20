package com.JavaProyect.ChallengeBackend.Auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserDao extends JpaRepository<ApplicationUser, Long>{

    public abstract Optional<ApplicationUser> findByUsername(String Username);
    
}
