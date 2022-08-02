package com.example.Alkemy.Disney.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String image;

    private String title;

    private LocalDate date;

    private int rating;

    @OneToMany(mappedBy="movie", fetch=FetchType.EAGER)
    private Set<MovieCharacter> movieCharacters = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gender_id")
    private Gender gender;

    public Movie() {
    }

    public Movie(String image, String title, LocalDate date, int rating, Gender gender) {
        this.image = image;
        this.title = title;
        this.date = date;
        this.rating = rating;
        this.gender = gender;
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

    public Set<MovieCharacter> getMovieCharacters() {
        return movieCharacters;
    }
    public void setMovieCharacters(Set<MovieCharacter> movieCharacters) {
        this.movieCharacters = movieCharacters;
    }

    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
