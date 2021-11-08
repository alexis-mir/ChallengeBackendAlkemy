package com.challenge.java.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Alexis
 */
@Data
public class CharacterRequestDTO {
    @NotNull
    private String image;
    @NotNull
    private String name;
    @NotNull
    private Integer age;
    private Float weight;
    private String history;
    private List<Long> moviesId;
}
