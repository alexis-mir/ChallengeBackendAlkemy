package com.challenge.java.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String image;
    @NotNull
    private String name;
    private Integer age;
    private Float weight;
    private String history;
    @ManyToMany
    @JoinTable(
            name = "characters_movies",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> movies;
}
