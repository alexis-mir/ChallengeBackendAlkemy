package com.challenge.java.mapper;

import com.challenge.java.dto.MovieRequestDTO;
import com.challenge.java.dto.MovieResponseDTO;
import com.challenge.java.model.Genre;
import com.challenge.java.model.Movie;
import com.challenge.java.model.Personaje;
import com.challenge.java.service.GenreService;
import com.challenge.java.service.PersonajeService;
import org.mapstruct.*;

import java.util.List;

/**
 * @author Alexis
 */
@Mapper(componentModel = "Spring", uses = {PersonajeService.class, GenreService.class})
public interface MovieMapper {
    @Mapping(target = "characters", source = "charactersId")
    @Mapping(target = "genres", source = "genresId")
    Movie toMovie(MovieRequestDTO movieRequestDTO);

    @Mapping(target = "charactersId", source = "characters")
    @Mapping(target = "genresId", source = "genres")
    MovieResponseDTO toDto(Movie movie);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "score", ignore = true)
    @Mapping(target = "charactersId", ignore = true)
    @Mapping(target = "genresId", ignore = true)
    MovieResponseDTO toSimpleDto(Movie movie);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "characters", source = "charactersId")
    @Mapping(target = "genres", source = "genresId")
    void updateFromDTO(MovieRequestDTO movieRequestDTO, @MappingTarget Movie movie);

    List<Long> charactersToListId(List<Personaje> characters);

    default Long characterToId(Personaje character) {
        return character.getId();
    }

    List<Long> genresToListId(List<Genre> genres);

    default Long genreToId(Genre genre){
        return genre.getId();
    }

}
