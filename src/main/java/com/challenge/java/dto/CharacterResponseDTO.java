package com.challenge.java.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author Alexis
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharacterResponseDTO {
    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Float weight;
    private String history;
    private List<Long> moviesId;
}
