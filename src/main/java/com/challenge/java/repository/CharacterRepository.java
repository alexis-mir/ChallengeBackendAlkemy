package com.challenge.java.repository;

import java.util.List;

import com.challenge.java.model.Character;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
     List<Character> findByName(String name);
     List<Character> findByAge(Integer age);
}
