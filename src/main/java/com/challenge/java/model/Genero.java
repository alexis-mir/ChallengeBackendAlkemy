package com.challenge.java.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    
    private String nombre;
    private String imagen;

    @ManyToMany
    @JoinTable(
        name = "genero_pelicula", 
        joinColumns = @JoinColumn(name = "genero_id"), 
        inverseJoinColumns = @JoinColumn(name = "pelicula_id")
    )
    private List<Movie> peliculasSeriesG;



}
