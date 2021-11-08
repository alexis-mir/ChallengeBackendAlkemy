package com.challenge.java.service;

import com.challenge.java.dto.MovieRequestDTO;
import com.challenge.java.dto.MovieResponseDTO;
import com.challenge.java.model.Movie;

import java.util.*;

/**
 * @author Alexis
 */
public interface MovieService {
    Movie findById(Long id);

    MovieResponseDTO findDtoById(Long id);

    List<MovieResponseDTO> findDtoAll(String title, Long genreId, String orderBy);

    MovieResponseDTO save(MovieRequestDTO movieRequestDTO);

    MovieResponseDTO update(MovieRequestDTO movieRequestDTO, Long id);

    void deleteById(Long id);
}
