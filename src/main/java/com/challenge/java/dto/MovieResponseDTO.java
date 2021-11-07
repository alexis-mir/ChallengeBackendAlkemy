package com.challenge.java.dto;

import com.challenge.java.model.Genero;
import com.challenge.java.model.Personaje;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Alexis
 */
@Data
public class MovieResponseDTO {
    private Long id;
    private String image;
    private String title;
    private LocalDateTime createAt;
    private Float score;
    private List<Personaje> characters;
    private List<Genero> genres;
}
