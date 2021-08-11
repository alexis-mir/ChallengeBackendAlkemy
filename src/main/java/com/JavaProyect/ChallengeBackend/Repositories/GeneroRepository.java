package com.JavaProyect.ChallengeBackend.Repositories;

import com.JavaProyect.ChallengeBackend.Models.Genero;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends CrudRepository<Genero, Long>{
}
