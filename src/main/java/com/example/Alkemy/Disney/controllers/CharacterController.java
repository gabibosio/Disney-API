package com.example.Alkemy.Disney.controllers;

import com.example.Alkemy.Disney.Services.MovieCharacterService;
import com.example.Alkemy.Disney.Services.CharacterService;
import com.example.Alkemy.Disney.dtos.MovieCharacterDTO;
import com.example.Alkemy.Disney.dtos.CharacterDTO;
import com.example.Alkemy.Disney.models.MovieCharacter;
import com.example.Alkemy.Disney.models.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/characters")
public class CharacterController {


    @Autowired
    private CharacterService characterService;

    @Autowired
    private MovieCharacterService movieCharacterService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestParam String img,@RequestParam String name,
                                                  @RequestParam int age, @RequestParam String weight,
                                                  @RequestParam String history){
        if(img.isEmpty() || name.isEmpty() || weight.isEmpty() || history.isEmpty() || age <= 0 ){
            return new ResponseEntity<>("Missing Data",HttpStatus.FORBIDDEN);
        }
        Character character = new Character(img,name,age,weight,history);
        characterService.saveCharacter(character);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<Object> edit(@PathVariable long id,@RequestParam String img,@RequestParam String name,
                                                  @RequestParam int age, @RequestParam String weight,
                                                  @RequestParam String history){
        Character character = characterService.characterById(id);
        if(img.isEmpty() || name.isEmpty() || weight.isEmpty() || history.isEmpty() || age <= 0 ){
            return new ResponseEntity<>("Missing Data",HttpStatus.FORBIDDEN);
        }
        if(character == null){
            return new ResponseEntity<>("character not found",HttpStatus.FORBIDDEN);
        }

            character.setImage(img);
            character.setName(name);
            character.setAge(age);
            character.setWeight(weight);
            character.setHistory(history);
            characterService.saveCharacter(character);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Object> delete(@PathVariable long id){
        Character character = characterService.characterById(id);
        if(character == null){
            return new ResponseEntity<>("character not found",HttpStatus.FORBIDDEN);
        }
        Set<MovieCharacter> movies = character.getMovieCharacters();
        movies.forEach(movieCharacter -> movieCharacterService.deleteMovieCharacter(movieCharacter));
        characterService.deleteCharacter(character);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @GetMapping(params = "name")
    public List<CharacterDTO> filterName(@RequestParam String name){
        if(name.isEmpty()){
            return characterService.getCharacters();
        }
        return characterService.getCharacterByName(name);
    }

    @GetMapping(params = "age")
    public List<CharacterDTO> filterAge(@RequestParam int age){
        return characterService.getCharacterByAge(age);
    }

    @GetMapping(params = "movies")
    public List<MovieCharacterDTO> filterId(@RequestParam long movies){
        return movieCharacterService.movieCharacterId(movies);
    }

}


