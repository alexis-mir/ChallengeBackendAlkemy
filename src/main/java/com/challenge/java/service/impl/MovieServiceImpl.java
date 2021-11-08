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
import com.challenge.java.model.Genero;
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
        //TODO: Refactor the code to filter by Query
        Collection<Predicate<Movie>> predicates = new ArrayList<>();

        Predicate<Movie> titlePredicate = m -> m.getTitle().equals(title);
        Predicate<Movie> genreIdPredicate = m -> m.getGenres().stream()
                .map(Genero::getId)
                .collect(Collectors.toList())
                .contains(genreId);

        if (title != null) predicates.add(titlePredicate);
        if (genreId != null) predicates.add(genreIdPredicate);
        Predicate<Movie> filter = predicates.stream()
                .reduce(Predicate::and)
                .orElse(x -> true);

        if (!predicates.isEmpty()) {
            if (orderBy != null) {
                if (orderBy.equalsIgnoreCase("DESC"))
                    return movieRepository.findAllByOrderByIdDesc().stream()
                            .filter(filter)
                            .map(movieMapper::toDto)
                            .collect(Collectors.toList());
            } else {
                return movieRepository.findAllByOrderByIdAsc().stream()
                        .filter(filter)
                        .map(movieMapper::toDto)
                        .collect(Collectors.toList());
            }
        }
        return movieRepository.findAll().stream()
                .map(movieMapper::toSimpleDto)
                .collect(Collectors.toList());

//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
//        Root<Movie> customer = criteriaQuery.from(Movie.class);
//
//        //Constructing list of parameters
//        List<Predicate> predicates = new ArrayList<>();
//
//        //Adding predicates in case of parameter not being null
//        if (title != null) {
//            predicates.add(
//                    criteriaBuilder.equal(customer.get("title"), title));
//        }
//        if (genreId != null) {
//            Join<Movie, Genero> join = customer.join("genres", JoinType.INNER);
//            predicates.add(
//                    criteriaBuilder.equal(join.get("movies").as(Long.class), genreId));
//        }
//        //query itself
//        criteriaQuery.select(customer)
//                .where(predicates.toArray(new Predicate[]{}));
//        //execute query and do something with result
//        entityManager.createQuery(criteriaQuery).getResultList().forEach(System.out::println);
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
