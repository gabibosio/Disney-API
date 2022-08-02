package com.example.Alkemy.Disney.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String name;

    private String image;


    @OneToMany(mappedBy="gender", fetch=FetchType.EAGER)
    private Set<Movie> movie = new HashSet<>();

    public Gender() {
    }

    public Gender(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public Set<Movie> getMovie() {
        return movie;
    }
    public void setMovie(Set<Movie> movie) {
        this.movie = movie;
    }
}
