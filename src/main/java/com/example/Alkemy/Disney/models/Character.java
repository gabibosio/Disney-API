package com.example.Alkemy.Disney.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String image;

    private String name;

    private int age;

    private String weight;

    private String history;

    @OneToMany(mappedBy="character", fetch=FetchType.EAGER)
    private Set<MovieCharacter> movieCharacters = new HashSet<>();


    public Character() {
    }

    public Character(String image, String name, int age, String weight, String history) {
        this.image = image;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.history = history;
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

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHistory() {
        return history;
    }
    public void setHistory(String history) {
        this.history = history;
    }

    public Set<MovieCharacter> getMovieCharacters() {
        return movieCharacters;
    }
    public void setMovieCharacters(Set<MovieCharacter> movieCharacters) {
        this.movieCharacters = movieCharacters;
    }
}
