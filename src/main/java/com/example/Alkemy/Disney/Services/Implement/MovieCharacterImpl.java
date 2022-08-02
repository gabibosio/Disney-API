package com.example.Alkemy.Disney.Services.Implement;

import com.example.Alkemy.Disney.Services.MovieCharacterService;
import com.example.Alkemy.Disney.dtos.MovieCharacterDTO;
import com.example.Alkemy.Disney.models.MovieCharacter;
import com.example.Alkemy.Disney.repositories.MovieCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieCharacterImpl implements MovieCharacterService {
    @Autowired
    private MovieCharacterRepository movieCharacterRepository;

    @Override
    public void saveMovieCharacter(MovieCharacter movieCharacter) {
        movieCharacterRepository.save(movieCharacter);
    }

    @Override
    public void deleteMovieCharacter(MovieCharacter movieCharacter) {
        movieCharacterRepository.delete(movieCharacter);
    }

    @Override
    public List<MovieCharacterDTO> movieCharacterId(long id) {
        return movieCharacterRepository.findAll().stream().filter(movieCharacter -> movieCharacter.getMovie().getId() == id).map(movieCharacter -> new MovieCharacterDTO(movieCharacter)).collect(Collectors.toList());
    }
}
