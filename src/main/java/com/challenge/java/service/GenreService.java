package com.challenge.java.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.challenge.java.exception.NotFoundException;
import com.challenge.java.model.Genre;
import com.challenge.java.repository.GenreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroService {
    @Autowired
    GenreRepository genreRepository;

    public Genre findById(Long id){
        return genreRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Genre not found"));
    }

    public Optional<Genre> buscarPorId(Long id){
        return genreRepository.findById(id);
    }

    public List<Genre> aGeneros(List<Long> generosId){
        return generosId.stream().map(g -> genreRepository.findById(g).get()).collect(Collectors.toList());
    }
}
