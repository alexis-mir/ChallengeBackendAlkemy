package com.challenge.java.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Alexis
 */
@Data
public class MovieRequestDTO {
    private String image;
    @NotNull
    private String title;
    private Float score;
    private List<Long> charactersId;
    private List<Long> genresId;
}
