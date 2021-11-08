package com.challenge.java.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.challenge.java.dto.MovieRequestDTO;
import com.challenge.java.dto.MovieResponseDTO;
import com.challenge.java.exception.NotFoundException;
import com.challenge.java.mapper.MovieMapper;
import com.challenge.java.model.Movie;
import com.challenge.java.repository.MovieRepository;

import com.challenge.java.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieMapper movieMapper;

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Movie Not Found."));
    }

    @Override
    public MovieResponseDTO findDtoById(Long id) {
        return movieMapper.toDto(findById(id));
    }

    @Override
    public List<MovieResponseDTO> findDtoAll(String title, Long genreId, String orderBy) {
        //TODO: Hay que refactorizar el codigo para hacer el filtrado por Query
        List<Movie> peliculasEnCrudo = movieRepository.findAll();
        List<MovieResponseDTO> peliculas = new ArrayList<>();
        Collection<Predicate<Movie>> filtros = new ArrayList<>();

        Predicate<Movie> filtroTitulo = p -> p.getTitle().equals(title);
        Predicate<Movie> filtroGeneroId = p -> p.getGenres().stream().map(g -> g.getId()).collect(Collectors.toList()).contains(genreId);
        if (title != null) filtros.add(filtroTitulo);
        if (genreId != null) filtros.add(filtroGeneroId);
        Predicate<Movie> filtro = filtros.stream().reduce(Predicate::and).orElse(x -> true);

        if (orderBy != null) {
            if (orderBy.equalsIgnoreCase("DESC")) {
                ordenarDesc(peliculasEnCrudo);
            } else if (orderBy.equalsIgnoreCase("ASC")) {
                ordenarAsc(peliculasEnCrudo);
            }
        }

//        if (filtros.isEmpty()) {
//            peliculasEnCrudo.forEach(p -> peliculas.add(aPeliculaDTO(p, true)));
//        } else {
//            peliculasEnCrudo.stream().filter(filtro).forEach(p -> peliculas.add(aPeliculaDTO(p)));
//        }

        return peliculas;
    }

    @Override
    public MovieResponseDTO save(MovieRequestDTO movieRequestDTO) {
        return movieMapper.toDto(movieRepository.save(movieMapper.toMovie(movieRequestDTO)));
    }

    @Override
    public MovieResponseDTO update(MovieRequestDTO movieRequestDTO, Long id) {
        Movie movie = findById(id);
        movieMapper.updateFromDTO(movieRequestDTO, movie);
        return movieMapper.toDto(movieRepository.save(movie));
    }

    @Override
    public void deleteById(Long id) {
        if (!movieRepository.existsById(id))
            throw new NotFoundException("Movie Not Found.");
        movieRepository.deleteById(id);
    }

    private List<Movie> ordenarDesc(List<Movie> peliculas) {
        peliculas.sort((x, y) -> y.getId().compareTo(x.getId()));
        return peliculas;
    }

    private List<Movie> ordenarAsc(List<Movie> peliculas) {
        peliculas.sort((x, y) -> x.getId().compareTo(y.getId()));
        return peliculas;
    }
}
