package com.JavaProyect.ChallengeBackend.Repositories;

import java.util.List;

import com.JavaProyect.ChallengeBackend.Models.PeliculaSerie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaSerieRepository extends JpaRepository<PeliculaSerie, Long> {
    public abstract List<PeliculaSerie> findByTitulo(String titulo);
    public List<PeliculaSerie> findAllByOrderByIdAsc();
    public abstract List<PeliculaSerie> findAllByOrderByIdDesc();
}
