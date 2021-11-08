package com.challenge.java.service.impl;

import com.challenge.java.exception.NotFoundException;
import com.challenge.java.model.Genre;
import com.challenge.java.repository.GenreRepository;
import com.challenge.java.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alexis
 */
@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    GenreRepository genreRepository;

    @Override
    public Genre findById(Long id){
        return genreRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Genre not found"));
    }
}
