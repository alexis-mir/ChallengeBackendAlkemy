package com.JavaProyect.ChallengeBackend.DTO;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonajeSimpleDTO implements Serializable {
    private Long id;
    private String imagen;
    private String nombre;
    private Integer edad;
    private Float peso;
    private String historia;
    private List<Long> peliculasSeriesId;
}
