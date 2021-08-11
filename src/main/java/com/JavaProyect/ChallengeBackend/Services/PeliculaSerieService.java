package com.JavaProyect.ChallengeBackend.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.JavaProyect.ChallengeBackend.DTO.PeliculaDTO;
import com.JavaProyect.ChallengeBackend.Models.Genero;
import com.JavaProyect.ChallengeBackend.Models.PeliculaSerie;
import com.JavaProyect.ChallengeBackend.Models.Personaje;
import com.JavaProyect.ChallengeBackend.Repositories.PeliculaSerieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PeliculaSerieService {
    @Autowired
    PeliculaSerieRepository peliculaSerieRepository;
    @Autowired
    PersonajeService personajeService;
    @Autowired
    GeneroService generoService;

    public Optional<PeliculaDTO> obtenerPorId(Long id){
        return Optional.of(aPeliculaDTO(peliculaSerieRepository.findById(id).get()));
    }

    public List<PeliculaDTO> obtenerPeliculas(String nombre, Long generoId, String ordenTipo){
        List<PeliculaSerie> peliculasEnCrudo = peliculaSerieRepository.findAll();
        List<PeliculaDTO> peliculas = new ArrayList<>();
        Collection<Predicate<PeliculaSerie>> filtros = new ArrayList<>();

        Predicate<PeliculaSerie> filtroTitulo = p -> p.getTitulo().equals(nombre);
        Predicate<PeliculaSerie> filtroGeneroId = p -> p.getGeneros().stream().map(g -> g.getId()).collect(Collectors.toList()).contains(generoId);
        if (nombre != null)filtros.add(filtroTitulo);
        if (generoId != null)filtros.add(filtroGeneroId);
        Predicate<PeliculaSerie> filtro = filtros.stream().reduce(Predicate::and).orElse(x->true);

        if (ordenTipo != null){
            if (ordenTipo.equalsIgnoreCase("DESC")){
                ordenarDesc(peliculasEnCrudo);
            } else if (ordenTipo.equalsIgnoreCase("ASC")){
                ordenarAsc(peliculasEnCrudo);
            }
        }
        
        if (filtros.isEmpty()){
            peliculasEnCrudo.forEach(p -> peliculas.add(aPeliculaDTO(p, true)));
        } else {
            peliculasEnCrudo.stream().filter(filtro).forEach(p -> peliculas.add(aPeliculaDTO(p)));
        }
        
        return peliculas;
    }

    public Optional<PeliculaDTO> guardarPelicula(PeliculaDTO peli){
            return Optional.of(aPeliculaDTO(peliculaSerieRepository.save(aPeliculaSerie(peli))));
    }

    public Optional<PeliculaDTO> actualizarPelicula(PeliculaDTO peli , Long id){
        if (existePelicula(id)) {
            return Optional.of(aPeliculaDTO(peliculaSerieRepository.save(aPeliculaSerie(peli))));
        } else {
            return Optional.empty();
        }
    }

    public boolean eliminarPelicula(Long id){
        try {
            peliculaSerieRepository.deleteById(id);
            return true;
        } catch (Exception err){
            return false;
        }
    }

    public boolean existePelicula(Long id){
        try {
            peliculaSerieRepository.findById(id);
            return true;
        } catch (Exception err){
            return false;
        }
    }

    public PeliculaSerie aPeliculaSerie(PeliculaDTO peli){
        
        return PeliculaSerie.builder()
        .id(peli.getId())
        .imagen(peli.getImagen())
        .titulo(peli.getTitulo())
        .fechaCreacion(peli.getFechaCreacion())
        .calificacion(peli.getCalificacion())
        .personajes(personajeService.aPersonajes(peli.getPersonajesId()))
        .generos(generoService.aGeneros(peli.getGenerosId()))
        .build();
    }

    public PeliculaDTO aPeliculaDTO(PeliculaSerie peli){
        
        return PeliculaDTO.builder()
        .id(peli.getId())
        .imagen(peli.getImagen())
        .titulo(peli.getTitulo())
        .fechaCreacion(peli.getFechaCreacion())
        .calificacion(peli.getCalificacion())
        .personajesId(peli.getPersonajes().stream().map(Personaje::getId).collect(Collectors.toList()))
        .generosId(peli.getGeneros().stream().map(Genero::getId).collect(Collectors.toList()))
        .build();
    }

    public PeliculaDTO aPeliculaDTO(PeliculaSerie peli, boolean simplificado){
        if (simplificado) {
            return PeliculaDTO.builder()
            .imagen(peli.getImagen())
            .titulo(peli.getTitulo())
            .fechaCreacion(peli.getFechaCreacion())
            .build();
        } else {
            return aPeliculaDTO(peli);
        }
        
    }
    public List<PeliculaSerie> ordenarDesc (List<PeliculaSerie> peliculas){
        Collections.sort(peliculas, (x, y) -> y.getId().compareTo(x.getId()));
        return peliculas;
    }
    public List<PeliculaSerie> ordenarAsc (List<PeliculaSerie> peliculas){
        Collections.sort(peliculas, (x, y) -> x.getId().compareTo(y.getId()));
        return peliculas;
    }
}
