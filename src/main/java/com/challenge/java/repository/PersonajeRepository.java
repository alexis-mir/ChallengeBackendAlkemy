package com.challenge.java.repository;

import java.util.ArrayList;

import com.challenge.java.model.Personaje;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PersonajeRepository extends CrudRepository<Personaje, Long>{

    public abstract ArrayList<Personaje> findByNombre(String nombre);
    public abstract ArrayList<Personaje> findByEdad(Integer edad);
}
