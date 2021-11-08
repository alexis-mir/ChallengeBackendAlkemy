package com.challenge.java.mapper;

import com.challenge.java.dto.CharacterRequestDTO;
import com.challenge.java.dto.CharacterResponseDTO;
import com.challenge.java.dto.MovieRequestDTO;
import com.challenge.java.model.Character;
import com.challenge.java.model.Movie;
import com.challenge.java.service.MovieService;
import org.mapstruct.*;

import java.util.List;

/**
 * @author Alexis
 */
@Mapper(componentModel = "Spring", uses = {MovieService.class})
public interface CharacterMapper {
    @Mapping(target = "movies",source = "moviesId")
    Character toCharacter(CharacterRequestDTO characterRequestDTO);
    @Mapping(target = "moviesId",source = "movies")
    CharacterResponseDTO toDto(Character character);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "age", ignore = true)
    @Mapping(target = "weight", ignore = true)
    @Mapping(target = "history", ignore = true)
    @Mapping(target = "moviesId", ignore = true)
    CharacterResponseDTO toSimpleDto(Character character);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "movies",source = "moviesId")
    void updateFromDTO(CharacterRequestDTO characterRequestDTO, @MappingTarget Character character);

    List<Long> moviesToListId(List<Movie> movies);

    default Long movieToId(Movie movie) {
        return movie.getId();
    }

}
