package com.JavaProyect.ChallengeBackend.Repositories;

import java.util.ArrayList;

import com.JavaProyect.ChallengeBackend.Models.Personaje;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PersonajeRepository extends CrudRepository<Personaje, Long>{

    public abstract ArrayList<Personaje> findByNombre(String nombre);
    public abstract ArrayList<Personaje> findByEdad(Integer edad);
}
