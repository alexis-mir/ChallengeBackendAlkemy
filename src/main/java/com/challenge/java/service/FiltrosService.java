package com.challenge.java.service;

import java.util.ArrayList;

import com.challenge.java.dto.PeliculaDTO;
import com.challenge.java.dto.PersonajeSimpleDTO;
import com.challenge.java.repository.GenreRepository;
import com.challenge.java.repository.MovieRepository;
import com.challenge.java.repository.PersonajeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FiltrosService {

    @Autowired
    PersonajeRepository personajeRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    GenreRepository genreRepository;

    public ArrayList<PersonajeSimpleDTO> filtrarPersonajePorPeliculaId(ArrayList<PersonajeSimpleDTO> personajes, Long peliculaId){
        
        
        return null;
    }

    public ArrayList<PeliculaDTO> filtrarPeliculaPorGenero(PeliculaDTO pelicula){
        return null;
    }

}
