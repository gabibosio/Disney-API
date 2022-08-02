package com.example.Alkemy.Disney.Services.Implement;

import com.example.Alkemy.Disney.Services.CharacterService;
import com.example.Alkemy.Disney.dtos.CharacterDTO;
import com.example.Alkemy.Disney.models.Character;
import com.example.Alkemy.Disney.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CharacterImpl implements CharacterService {
    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public void saveCharacter(Character character) {
        characterRepository.save(character);
    }

    @Override
    public Character characterById(long id) {
        return characterRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCharacter(Character character) {
        characterRepository.delete(character);
    }

    @Override
    public List<Character> characters(Set<Long> id) {
        return characterRepository.findAllById(id);
    }

    @Override
    public List<CharacterDTO> getCharacters() {
        return characterRepository.findAll().stream().map(characters -> new CharacterDTO(characters)).collect(Collectors.toList());
    }

    @Override
    public List<CharacterDTO> getCharacterByName(String name) {
        return characterRepository.findAll().stream().filter(character -> character.getName().equals(name)).map(character -> new CharacterDTO(character)).collect(Collectors.toList());
    }

    @Override
    public List<CharacterDTO> getCharacterByAge(int age) {
        return characterRepository.findAll().stream().filter(character -> character.getAge() == age).map(character -> new CharacterDTO(character)).collect(Collectors.toList());
    }


}
