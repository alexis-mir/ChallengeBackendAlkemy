package com.challenge.java.repository;

import com.challenge.java.model.Genre;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long>{
}
