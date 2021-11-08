package com.challenge.java.service.impl;

import com.challenge.java.dto.CharacterRequestDTO;
import com.challenge.java.dto.CharacterResponseDTO;
import com.challenge.java.exception.NotFoundException;
import com.challenge.java.mapper.CharacterMapper;
import com.challenge.java.model.Character;
import com.challenge.java.model.Movie;
import com.challenge.java.repository.CharacterRepository;
import com.challenge.java.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Alexis
 */
@Service
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    CharacterMapper characterMapper;

    @Override
    public Character findById(Long id){
        return characterRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Character not found"));
    }

    @Override
    public CharacterResponseDTO findDtoById(Long id) {
        return characterMapper.toDto(findById(id));
    }

    @Override
    public List<CharacterResponseDTO> findDtoAll(String name, Integer age, Long movieId) {
        //TODO: Refactor the code to filter by Query
        Collection<Predicate<Character>> predicates = new ArrayList<>();

        Predicate<Character> namePredicate = m -> m.getName().equals(name);
        Predicate<Character> agePredicate = m -> m.getAge().equals(age);
        Predicate<Character> movieIdPredicate = m -> m.getMovies().stream()
                .map(Movie::getId)
                .collect(Collectors.toList())
                .contains(movieId);

        if (name != null) predicates.add(namePredicate);
        if (name != null) predicates.add(agePredicate);
        if (movieId != null) predicates.add(movieIdPredicate);
        Predicate<Character> filter = predicates.stream()
                .reduce(Predicate::and)
                .orElse(x -> true);
        if (!predicates.isEmpty()) {
                    return characterRepository.findAll().stream()
                            .filter(filter)
                            .map(characterMapper::toDto)
                            .collect(Collectors.toList());
        }
        return characterRepository.findAll().stream()
                .map(characterMapper::toSimpleDto)
                .collect(Collectors.toList());
    }

    @Override
    public CharacterResponseDTO save(CharacterRequestDTO characterRequestDTO) {
        return characterMapper.toDto(characterRepository.save(characterMapper.toCharacter(characterRequestDTO)));
    }

    @Override
    public CharacterResponseDTO update(CharacterRequestDTO characterRequestDTO, Long id) {
        Character character = findById(id);
        characterMapper.updateFromDTO(characterRequestDTO, character);
        return characterMapper.toDto(characterRepository.save(character));
    }

    @Override
    public void deleteById(Long id) {
        if (!characterRepository.existsById(id))
            throw new NotFoundException("Character Not Found.");
        characterRepository.deleteById(id);
    }
}
