package com.example.Alkemy.Disney.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String imagen;

    private String titulo;

    private LocalDate fecha;

    private int calificacion;

    @OneToMany(mappedBy="pelicula", fetch=FetchType.EAGER)
    private Set<PeliculaPersonaje> peliculaPersonajes = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="genero_id")
    private Genero genero;

    public Pelicula() {
    }

    public Pelicula(String imagen, String titulo, LocalDate fecha, int calificacion, Genero genero) {
        this.imagen = imagen;
        this.titulo = titulo;
        this.fecha = fecha;
        this.calificacion = calificacion;
        this.genero = genero;
    }

    public long getId() {
        return id;
    }


    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getCalificacion() {
        return calificacion;
    }
    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Set<PeliculaPersonaje> getPeliculaPersonajes() {
        return peliculaPersonajes;
    }
    public void setPeliculaPersonajes(Set<PeliculaPersonaje> peliculaPersonajes) {
        this.peliculaPersonajes = peliculaPersonajes;
    }

    public Genero getGenero() {
        return genero;
    }
    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}
