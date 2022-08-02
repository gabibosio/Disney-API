package com.example.Alkemy.Disney.dtos;

import com.example.Alkemy.Disney.models.MovieCharacter;

public class MovieCharacterDTO {


    private CharacterDTO character;


    public MovieCharacterDTO() {
    }

    public MovieCharacterDTO(MovieCharacter movieCharacter) {
        this.character = new CharacterDTO(movieCharacter.getCharacter());
    }


    public CharacterDTO getCharacter() {
        return character;
    }
    public void setCharacter(CharacterDTO character) {
        this.character = character;
    }
}
