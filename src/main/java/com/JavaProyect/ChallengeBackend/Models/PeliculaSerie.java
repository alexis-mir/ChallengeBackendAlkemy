package com.JavaProyect.ChallengeBackend.Models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@Table(name = "pelicula_serie")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaSerie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String imagen;
    private String titulo;
    
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    private Float calificacion;

    @ManyToMany(mappedBy = "peliculasSeries")
    private List<Personaje> personajes;

    @ManyToMany(mappedBy = "peliculasSeriesG")
    private List<Genero> generos;


}
