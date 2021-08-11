package com.JavaProyect.ChallengeBackend.Services;

import java.util.ArrayList;

import com.JavaProyect.ChallengeBackend.DTO.PeliculaDTO;
import com.JavaProyect.ChallengeBackend.DTO.PersonajeSimpleDTO;
import com.JavaProyect.ChallengeBackend.Repositories.GeneroRepository;
import com.JavaProyect.ChallengeBackend.Repositories.PeliculaSerieRepository;
import com.JavaProyect.ChallengeBackend.Repositories.PersonajeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FiltrosService {

    @Autowired
    PersonajeRepository personajeRepository;
    @Autowired
    PeliculaSerieRepository peliculaSerieRepository;
    @Autowired
    GeneroRepository generoRepository;

    public ArrayList<PersonajeSimpleDTO> filtrarPersonajePorPeliculaId(ArrayList<PersonajeSimpleDTO> personajes, Long peliculaId){
        
        
        return null;
    }

    public ArrayList<PeliculaDTO> filtrarPeliculaPorGenero(PeliculaDTO pelicula){
        return null;
    }

}
