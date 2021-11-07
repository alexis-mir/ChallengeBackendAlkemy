package com.challenge.java.repository;

import com.challenge.java.model.Genero;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends CrudRepository<Genero, Long>{
}
