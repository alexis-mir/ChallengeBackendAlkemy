package com.challenge.java.controller;

import java.util.List;

import com.challenge.java.dto.MovieRequestDTO;
import com.challenge.java.dto.MovieResponseDTO;
import com.challenge.java.service.impl.MovieServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieServiceImpl movieService;

    @GetMapping()
    public ResponseEntity<List<MovieResponseDTO>> findDtoAll(
            @RequestParam(value = "name", required = false) String title,
            @RequestParam(value = "genre", required = false) Long genreId,
            @RequestParam(value = "order", required = false) String orderBy) {
        return new ResponseEntity<>(movieService.findDtoAll(title, genreId, orderBy), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> findDtoById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(movieService.findDtoById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<MovieResponseDTO> save(@RequestBody MovieRequestDTO movieRequestDTO) {
        return new ResponseEntity<>(movieService.save(movieRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> update(
            @RequestBody MovieRequestDTO movieRequestDTO,
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(movieService.update(movieRequestDTO, id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        movieService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
