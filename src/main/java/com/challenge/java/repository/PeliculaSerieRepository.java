package com.challenge.java.repository;

import java.util.List;

import com.challenge.java.model.PeliculaSerie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaSerieRepository extends JpaRepository<PeliculaSerie, Long> {
    public abstract List<PeliculaSerie> findByTitulo(String titulo);
    public List<PeliculaSerie> findAllByOrderByIdAsc();
    public abstract List<PeliculaSerie> findAllByOrderByIdDesc();
}
