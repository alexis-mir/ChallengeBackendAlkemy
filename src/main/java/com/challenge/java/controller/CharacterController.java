package com.challenge.java.controller;

import java.util.List;

import com.challenge.java.dto.CharacterRequestDTO;
import com.challenge.java.dto.CharacterResponseDTO;
import com.challenge.java.service.CharacterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import javax.validation.Valid;


@RestController
@RequestMapping("/characters")
public class CharacterController {
    @Autowired
    CharacterService characterService;

    @GetMapping
    public ResponseEntity<List<CharacterResponseDTO>> findAllDto(
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "age", required = false) Integer age,
        @RequestParam(value = "movies", required = false) Long movieId) {

        return new ResponseEntity<>(characterService.findDtoAll(name, age, movieId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterResponseDTO> findDtoById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(characterService.findDtoById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CharacterResponseDTO> save(@Valid @RequestBody CharacterRequestDTO characterRequestDTO) {
        return new ResponseEntity<>(characterService.save(characterRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterResponseDTO> update(
            @Valid @RequestBody CharacterRequestDTO characterRequestDTO,
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(characterService.update(characterRequestDTO, id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        characterService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
