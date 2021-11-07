package com.challenge.java.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.challenge.java.dto.PersonajeSimpleDTO;
import com.challenge.java.model.Movie;
import com.challenge.java.model.Personaje;
import com.challenge.java.repository.PersonajeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonajeService {
    @Autowired
    PersonajeRepository personajeRepository;

    public ArrayList<PersonajeSimpleDTO> obtenerTodosPersonajes(){
        ArrayList<Personaje> todosPersonajes = (ArrayList<Personaje>) personajeRepository.findAll();
        ArrayList<PersonajeSimpleDTO> respuesta = new ArrayList<>();

        todosPersonajes.forEach(o -> respuesta.add(PersonajeSimpleDTO
                                                                .builder()
                                                                .id(o.getId())
                                                                .imagen(o.getImagen())
                                                                .nombre(o.getNombre())
                                                                .edad(o.getEdad())
                                                                .peso(o.getPeso())
                                                                .historia(o.getHistoria())
                                                                .peliculasSeriesId(
                                                                    o.getPeliculasSeries().stream().map(Movie::getId).collect(Collectors.toList())
                                                                    )
                                                                .build()
                                                                ));
        return respuesta;
    }

    public List<PersonajeSimpleDTO> obtenerPersonajes(){
        ArrayList<Personaje> todosPersonajes = (ArrayList<Personaje>) personajeRepository.findAll();
        List<PersonajeSimpleDTO> respuesta = new ArrayList<>();
        todosPersonajes.forEach(o -> respuesta.add(PersonajeSimpleDTO.builder().imagen(o.getImagen()).nombre(o.getNombre()).build()));
        return respuesta;
    }

    public Personaje guardarPersonaje(Personaje personaje){
        return personajeRepository.save(personaje);
    }

    public PersonajeSimpleDTO obtenerPorId(Long id){
        PersonajeSimpleDTO respuesta = personajeRepository.findById(id).map(p -> PersonajeSimpleDTO.builder()
            .id(p.getId())
            .imagen(p.getImagen())
            .nombre(p.getNombre())
            .edad(p.getEdad())
            .peso(p.getPeso())
            .historia(p.getHistoria())
            .peliculasSeriesId(
                p.getPeliculasSeries().stream().map(Movie::getId).collect(Collectors.toList())
            )
            .build()
        ).get();
        return respuesta;
    }

    public ArrayList<PersonajeSimpleDTO> obtenerPorNombre(String nombre){
        ArrayList<PersonajeSimpleDTO> respuesta = new ArrayList<>();

        personajeRepository.findByNombre(nombre).forEach(o -> respuesta.add(PersonajeSimpleDTO
        .builder()
        .id(o.getId())
        .imagen(o.getImagen())
        .nombre(o.getNombre())
        .edad(o.getEdad())
        .peso(o.getPeso())
        .historia(o.getHistoria())
        .peliculasSeriesId(
            o.getPeliculasSeries().stream().map(Movie::getId).collect(Collectors.toList())
        )
        .build()
        ));

        return respuesta;
    }

    public ArrayList<PersonajeSimpleDTO> obtenerPorEdad(Integer edad){
        ArrayList<PersonajeSimpleDTO> respuesta = new ArrayList<>();

        personajeRepository.findByEdad(edad).forEach(o -> respuesta.add(PersonajeSimpleDTO
        .builder()
        .id(o.getId())
        .imagen(o.getImagen())
        .nombre(o.getNombre())
        .edad(o.getEdad())
        .peso(o.getPeso())
        .historia(o.getHistoria())
        .peliculasSeriesId(
            o.getPeliculasSeries().stream().map(Movie::getId).collect(Collectors.toList())
        )
        .build()
        ));

        return respuesta;
    }
    public List<Personaje> aPersonajes(List<Long> personajesId){
        return personajesId.stream().map(p -> personajeRepository.findById(p).get()).filter(p -> p != null).collect(Collectors.toList());
    }

    public boolean eliminarPersonaje(Long id){
        try {
            personajeRepository.deleteById(id);
            return true;
        } catch (Exception err){
            return false;
        }
    }

    public boolean existePersonaje(Long id){
        try {
            personajeRepository.findById(id);
            return true;
        } catch (Exception err){
            return false;
        }
    }

}
