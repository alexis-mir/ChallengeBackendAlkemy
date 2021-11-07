package com.challenge.java.mapper;

import com.challenge.java.dto.MovieRequestDTO;
import com.challenge.java.dto.MovieResponseDTO;
import com.challenge.java.model.Movie;
import org.mapstruct.*;

/**
 * @author Alexis
 */
@Mapper(componentModel = "Spring")
public interface MovieMapper {
    Movie toMovie(MovieRequestDTO movieRequestDTO);
    MovieResponseDTO toDto(Movie movie);
    MovieResponseDTO toSimpleDto(Movie movie);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    //TODO:hay que mappear las relaciones
    void updateFromDTO(MovieRequestDTO movieRequestDTO, @MappingTarget Movie movie);

}
