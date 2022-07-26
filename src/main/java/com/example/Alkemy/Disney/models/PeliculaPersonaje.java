package com.example.Alkemy.Disney.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class PeliculaPersonaje {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="personaje_id")
    private Personaje personaje;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="pelicula_id")
    private Pelicula pelicula;

    public PeliculaPersonaje() {
    }

    public PeliculaPersonaje(Personaje personaje, Pelicula pelicula) {
        this.personaje = personaje;
        this.pelicula = pelicula;
    }

    public long getId() {
        return id;
    }

    public Personaje getPersonaje() {
        return personaje;
    }
    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }
    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
}
