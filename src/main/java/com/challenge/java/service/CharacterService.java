package com.challenge.java.service;

import java.util.List;

import com.challenge.java.dto.CharacterRequestDTO;
import com.challenge.java.dto.CharacterResponseDTO;
import com.challenge.java.model.Character;

public interface CharacterService {

    Character findById(Long id);
    CharacterResponseDTO findDtoById(Long id);
    List<CharacterResponseDTO> findDtoAll(String name, Integer age, Long movieId);
    CharacterResponseDTO save(CharacterRequestDTO characterRequestDTO);
    CharacterResponseDTO update(CharacterRequestDTO characterRequestDTO, Long id);
    void deleteById(Long id);
}
