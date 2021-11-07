package com.challenge.java.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@ToString
@Builder
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String imagen;
    private String nombre;
    private Integer edad;
    private Float peso;
    private String historia;
    @ManyToMany
    @JoinTable(
        name = "personaje_pelicula", 
        joinColumns = @JoinColumn(name = "personaje_id"), 
        inverseJoinColumns = @JoinColumn(name = "pelicula_id")
    )
    private List<PeliculaSerie> peliculasSeries;
    

}
