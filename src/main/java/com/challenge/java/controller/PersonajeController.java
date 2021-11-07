package com.challenge.java.controller;

import java.util.ArrayList;
import java.util.function.Predicate;

import com.challenge.java.dto.PersonajeSimpleDTO;
import com.challenge.java.model.Personaje;
import com.challenge.java.service.PeliculaSerieService;
import com.challenge.java.service.PersonajeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/characters")
public class PersonajeController {
    @Autowired
    PersonajeService personajeService;
    @Autowired
    PeliculaSerieService peliculaService;

    @GetMapping()
    public ArrayList<PersonajeSimpleDTO> obtenerPersonajes(
        @RequestParam(value = "name", required = false) String nombre,
        @RequestParam(value = "age", required = false) Integer edad,
        @RequestParam(value = "movies", required = false) Long peliculaId) {
        boolean hayNombre = nombre != null;
        boolean hayEdad = edad != null;
        boolean hayPeliculaId = peliculaId != null;

        ArrayList<PersonajeSimpleDTO> respuesta = new ArrayList<>();

        Predicate<PersonajeSimpleDTO> filtrarPorEdad = e -> e.getEdad() == edad;
        Predicate<PersonajeSimpleDTO> filtrarPorPelicula = pe -> pe.getPeliculasSeriesId().contains(peliculaId);

        if (hayNombre && hayEdad && hayPeliculaId){
            this.personajeService.obtenerPorNombre(nombre).stream().filter(filtrarPorEdad.and(filtrarPorPelicula)).forEach(p -> respuesta.add(p));
        } else if (hayNombre && hayEdad && !hayPeliculaId){
            this.personajeService.obtenerPorNombre(nombre).stream().filter(filtrarPorEdad).forEach(p -> respuesta.add(p));
        } else if (hayNombre && !hayEdad && hayPeliculaId){
            this.personajeService.obtenerPorNombre(nombre).stream().filter(filtrarPorPelicula).forEach(p -> respuesta.add(p));
        } else if (hayNombre && !hayEdad && !hayPeliculaId){
            this.personajeService.obtenerPorNombre(nombre).stream().forEach(p -> respuesta.add(p));
        } else if (!hayNombre && hayEdad && hayPeliculaId){
            this.personajeService.obtenerPorEdad(edad).stream().filter(filtrarPorPelicula).forEach( p -> respuesta.add(p));
        } else if (!hayNombre && hayEdad && !hayPeliculaId){
            this.personajeService.obtenerPorEdad(edad).stream().forEach( p -> respuesta.add(p));
        } else if (!hayNombre && !hayEdad && hayPeliculaId){
            this.personajeService.obtenerTodosPersonajes().stream().filter(filtrarPorPelicula).forEach(p -> respuesta.add(p));
        } else {
            this.personajeService.obtenerPersonajes().forEach(p -> respuesta.add(p));
        }
        return respuesta;
    }

    @GetMapping(path = "/{id}")
    public PersonajeSimpleDTO obtenerPersonajePorId(@PathVariable("id") Long id){
        return this.personajeService.obtenerPorId(id);
    }

    @GetMapping("/all")
    public ArrayList<PersonajeSimpleDTO> obtenerTodosPersonajes() {
        return personajeService.obtenerTodosPersonajes();
    }

    @PostMapping()
    public Personaje guardarPersonaje(@RequestBody Personaje personaje){
        if(personaje.getPeliculasSeries() != null) personaje.getPeliculasSeries().forEach(p -> this.peliculaService.guardarPelicula(peliculaService.aPeliculaDTO(p)));
        return this.personajeService.guardarPersonaje(personaje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personaje> actualizarPersonaje(@RequestBody Personaje personaje, @PathVariable("id") Long id){
        boolean ok = this.personajeService.existePersonaje(id);
        if (ok) {
            return ResponseEntity.ok().body(this.personajeService.guardarPersonaje(personaje));
        } else {
            return ResponseEntity.badRequest().body(personaje);
        }
    }
    
    @DeleteMapping(path = "/{id}")
    public String eliminarPersonajePorId(@PathVariable("id") Long id){
        boolean ok = this.personajeService.eliminarPersonaje(id);
        if (ok) {
            return "Se elimino el personaje con id " + id;
        } else {
            return "No se pudo eliminar el personaje con id " + id;
        }
    }
}
