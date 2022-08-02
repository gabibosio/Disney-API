package com.example.Alkemy.Disney.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class MovieCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="character_id")
    private Character character;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="movie_id")
    private Movie movie;

    public MovieCharacter() {
    }

    public MovieCharacter(Character character, Movie movie) {
        this.character = character;
        this.movie = movie;
    }

    public long getId() {
        return id;
    }

    public Character getCharacter() {
        return character;
    }
    public void setCharacter(Character character) {
        this.character = character;
    }

    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
