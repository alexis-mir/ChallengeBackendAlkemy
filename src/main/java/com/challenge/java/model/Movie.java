package com.challenge.java.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import lombok.*;

@Entity
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    @NotNull
    private String title;
    @NotNull
    private LocalDateTime createAt = LocalDateTime.now();
    private boolean deleted;
    private Float score;
    @ManyToMany(mappedBy = "peliculasSeries")
    private List<Personaje> characters;
    @ManyToMany(mappedBy = "movies")
    private List<Genero> genres;
}
