package com.challenge.java.repository;

import java.util.List;

import com.challenge.java.model.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    public abstract List<Movie> findByTitulo(String titulo);
    public List<Movie> findAllByOrderByIdAsc();
    public abstract List<Movie> findAllByOrderByIdDesc();
}
