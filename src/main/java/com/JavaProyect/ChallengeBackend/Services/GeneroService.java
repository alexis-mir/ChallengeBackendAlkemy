package com.JavaProyect.ChallengeBackend.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.JavaProyect.ChallengeBackend.Models.Genero;
import com.JavaProyect.ChallengeBackend.Repositories.GeneroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroService {
    @Autowired
    GeneroRepository generoRepository;

    public Optional<Genero> buscarPorId(Long id){
        return generoRepository.findById(id);
    }

    public List<Genero> aGeneros(List<Long> generosId){
        return generosId.stream().map(g -> generoRepository.findById(g).get()).collect(Collectors.toList());
    }
}
