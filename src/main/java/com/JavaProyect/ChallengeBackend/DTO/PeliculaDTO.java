package com.JavaProyect.ChallengeBackend.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PeliculaDTO implements Serializable {
    private Long id;

    private String imagen;
    private String titulo;
    private Date fechaCreacion;
    private Float calificacion;
    private List<Long> personajesId;
    private List<Long> generosId;
}
