package com.JavaProyect.ChallengeBackend.Repositories;

import com.JavaProyect.ChallengeBackend.Models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public abstract Usuario findByUsername(String username);
}
