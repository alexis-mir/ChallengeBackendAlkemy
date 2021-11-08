package com.challenge.java.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Alexis
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieResponseDTO {
    private Long id;
    private String image;
    private String title;
    private LocalDateTime createAt;
    private Float score;
    private List<Long> charactersId;
    private List<Long> genresId;
}
