package com.example.Alkemy.Disney.controllers;

import com.example.Alkemy.Disney.Services.GenderService;
import com.example.Alkemy.Disney.Services.MovieCharacterService;
import com.example.Alkemy.Disney.Services.MovieService;
import com.example.Alkemy.Disney.Services.CharacterService;
import com.example.Alkemy.Disney.dtos.MovieDTO;
import com.example.Alkemy.Disney.models.Gender;
import com.example.Alkemy.Disney.models.Movie;
import com.example.Alkemy.Disney.models.MovieCharacter;
import com.example.Alkemy.Disney.models.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MovieController {


    @Autowired
    private CharacterService characterService;

    @Autowired
    private GenderService genderService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieCharacterService movieCharacterService;


    @GetMapping("/movies")
    public List<MovieDTO> getMovies(){
        return movieService.getMovies();
    }


    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestParam String img, @RequestParam String title,
                                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                        @RequestParam int rating, @RequestParam long idGender,
                                        @RequestParam Set<Long> idCharacter){

        if(img.isEmpty() || title.isEmpty() || rating <=0){
            return new ResponseEntity<>("Missing Data",HttpStatus.FORBIDDEN);
        }

        List <Character> character = characterService.characters(idCharacter);
        Gender gender = genderService.genderById(idGender);

        Movie movie = new Movie(img,title,date,rating, gender);
        movieService.saveMovie(movie);
        List<MovieCharacter> movieCharacters = character.stream().map(character1 ->  new MovieCharacter(character1, movie)).collect(Collectors.toList());
        movieCharacters.forEach(movieCharacter -> movieCharacterService.saveMovieCharacter(movieCharacter));


        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<Object> edit(@PathVariable long id,@RequestParam String img, @RequestParam String title,
                                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                         @RequestParam int rating,@RequestParam long idGender){
        if(img.isEmpty() || title.isEmpty() || rating <=0){
            return new ResponseEntity<>("Missing Data",HttpStatus.FORBIDDEN);
        }

        Movie movie = movieService.movieById(id);
        if(movie == null){
            return new ResponseEntity<>("movie not found",HttpStatus.FORBIDDEN);
        }
        Gender gender = genderService.genderById(idGender);

        movie.setImage(img);
        movie.setTitle(title);
        movie.setDate(date);
        movie.setRating(rating);
        movie.setGender(gender);
        movieService.saveMovie(movie);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id){
        Movie movie = movieService.movieById(id);
        if(movie == null){
            return new ResponseEntity<>("movie not found",HttpStatus.FORBIDDEN);
        }

        Set<MovieCharacter> characters = movie.getMovieCharacters();
        characters.forEach(movieCharacter -> movieCharacterService.deleteMovieCharacter(movieCharacter));
        movieService.deleteMovie(movie);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Object> addCharacter (@PathVariable long idMovie,
                                                    @PathVariable long idCharacter){
        Movie movie = movieService.movieById(idMovie);
        if(movie == null){
            return new ResponseEntity<>("movie not found",HttpStatus.FORBIDDEN);
        }
        Character character = characterService.characterById(idCharacter);
        if(character == null){
            return new ResponseEntity<>("character not found",HttpStatus.FORBIDDEN);
        }

        MovieCharacter movieCharacter = new MovieCharacter(character, movie);
        Set<MovieCharacter> movieCharacters = movie.getMovieCharacters();
        movieCharacters.add(movieCharacter);
        movieCharacterService.saveMovieCharacter(movieCharacter);


        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Object> deleteCharacter (@PathVariable long idMovie,
                                                    @PathVariable long idCharacter){
        Movie movie = movieService.movieById(idMovie);
        if(movie == null){
            return new ResponseEntity<>("movie not found",HttpStatus.FORBIDDEN);
        }

        Character character = characterService.characterById(idCharacter);
        if(character == null){
            return new ResponseEntity<>("character not found",HttpStatus.FORBIDDEN);
        }


        Set<MovieCharacter> movieCharacters = movie.getMovieCharacters();
        List<MovieCharacter> movieCharacter = movieCharacters.stream().filter(movieCharacter1 ->  movieCharacter1.getCharacter().getId() == idCharacter).collect(Collectors.toList());
        movieCharacter.forEach(movieCharacter1 -> movieCharacterService.deleteMovieCharacter(movieCharacter1));

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(params = "name")
    public List<MovieDTO> filterName(@RequestParam String name){
        if(name.isEmpty()){
            return movieService.getMovies();
        }
        return movieService.getMovieByName(name);
    }

    @GetMapping(params = "genre")
    public List<MovieDTO> filterGender(@RequestParam long genre){
        return movieService.getMovieByGender(genre);
    }

    @GetMapping(params = "order")
    public List<MovieDTO> filterOrder(@RequestParam String order){

        if(order.equals("ASC")){
            List<MovieDTO> movies = movieService.getMovies();
            movies.sort((p1, p2) -> p1.getDate().compareTo(p2.getDate()));
            return movies;
        }
        if(order.equals("DESC")){
            List<MovieDTO> movies = movieService.getMovies();
            movies.sort((p1, p2) -> p2.getDate().compareTo(p1.getDate()));
            return movies;
        }

        if(order.isEmpty()){
            List<MovieDTO> movies = movieService.getMovies();
            return movies;
        }

        return null;
    }
}
