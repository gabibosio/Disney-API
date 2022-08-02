package com.example.Alkemy.Disney.Services;

import com.example.Alkemy.Disney.dtos.MovieCharacterDTO;
import com.example.Alkemy.Disney.models.MovieCharacter;

import java.util.List;

public interface MovieCharacterService {

    void saveMovieCharacter(MovieCharacter movieCharacter);

    void deleteMovieCharacter(MovieCharacter movieCharacter);

    List<MovieCharacterDTO> movieCharacterId(long id);
}
