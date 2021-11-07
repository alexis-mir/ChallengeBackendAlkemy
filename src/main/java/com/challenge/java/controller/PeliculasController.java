package com.challenge.java.controller;

import java.util.List;
import java.util.Optional;

import com.challenge.java.dto.PeliculaDTO;
import com.challenge.java.service.PeliculaSerieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/movies")
public class PeliculasController {
    @Autowired
    PeliculaSerieService peliculaSerieService;

    @GetMapping()
    public ResponseEntity<List<PeliculaDTO>> obtenerPeliculas(
        @RequestParam (value = "name", required = false) String nombre,
        @RequestParam (value = "genre", required = false) Long generoId,
        @RequestParam (value = "order", required = false) String orden){
        return ResponseEntity.ok().body(peliculaSerieService.obtenerPeliculas(nombre, generoId, orden));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> obtenerPeliculaPorId(@PathVariable("id") Long peliculaId){
        Optional<PeliculaDTO> respuesta = peliculaSerieService.obtenerPorId(peliculaId);
        if (respuesta.isPresent()) {
            return ResponseEntity.ok().body(respuesta.get());
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping()
    public ResponseEntity<PeliculaDTO> guardarPelicula(@RequestBody PeliculaDTO pelicula) {
        Optional<PeliculaDTO> respuesta = peliculaSerieService.guardarPelicula(pelicula);
        if (respuesta.isPresent()) {
            return ResponseEntity.ok().body(respuesta.get());
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> actualizarPelicula(@RequestBody PeliculaDTO pelicula , @PathVariable("id") Long peliculaId){
        Optional<PeliculaDTO> respuesta = peliculaSerieService.actualizarPelicula(pelicula, peliculaId);
        if (respuesta.isPresent()) {
            return ResponseEntity.ok().body(respuesta.get());
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPelicula(@PathVariable("id") Long peliculaId){
        boolean eliminado = peliculaSerieService.eliminarPelicula(peliculaId);
        String respuesta = eliminado ? "Se elimino el personaje con id " + peliculaId :"No se pudo eliminar el personaje con id " + peliculaId;
        if (eliminado) {
            return ResponseEntity.ok().body(respuesta);
        } else {
            return ResponseEntity.badRequest().body(respuesta);
        }
    }
}
