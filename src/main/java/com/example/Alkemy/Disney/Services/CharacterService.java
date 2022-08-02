package com.example.Alkemy.Disney.Services;

import com.example.Alkemy.Disney.dtos.CharacterDTO;
import com.example.Alkemy.Disney.models.Character;

import java.util.List;
import java.util.Set;

public interface CharacterService {
    void saveCharacter(Character character);

    Character characterById(long id);

    void deleteCharacter(Character character);

    List<Character> characters(Set<Long> id);

    List<CharacterDTO> getCharacters();

    List<CharacterDTO> getCharacterByName(String name);

    List<CharacterDTO> getCharacterByAge(int age);
}
