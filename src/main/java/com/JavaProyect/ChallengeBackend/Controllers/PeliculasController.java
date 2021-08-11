package com.JavaProyect.ChallengeBackend.Controllers;

import java.util.List;
import java.util.Optional;

import com.JavaProyect.ChallengeBackend.DTO.PeliculaDTO;
import com.JavaProyect.ChallengeBackend.Services.PeliculaSerieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
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
