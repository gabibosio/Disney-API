package com.example.Alkemy.Disney.dtos;

import com.example.Alkemy.Disney.models.Movie;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieDTO {

    private long id;

    private String image;

    private String title;

    private LocalDate date;

    private int rating;

    private Set<MovieCharacterDTO> characters = new HashSet<>();

    private GenderDTO gender;

    public MovieDTO() {
    }

    public MovieDTO(Movie movie){
        this.id = movie.getId();
        this.image = movie.getImage();
        this.title = movie.getTitle();
        this.date = movie.getDate();
        this.rating = movie.getRating();
        this.gender = new GenderDTO(movie.getGender());
        this.characters = movie.getMovieCharacters().stream().map(movieCharacter -> new MovieCharacterDTO(movieCharacter)).collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }


    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    public GenderDTO getGender() {
        return gender;
    }
    public void setGender(GenderDTO gender) {
        this.gender = gender;
    }

    public Set<MovieCharacterDTO> getCharacters() {
        return characters;
    }
    public void setCharacters(Set<MovieCharacterDTO> characters) {
        this.characters = characters;
    }

}
