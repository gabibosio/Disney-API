package com.example.Alkemy.Disney.dtos;

import com.example.Alkemy.Disney.models.Genero;
import com.example.Alkemy.Disney.models.Pelicula;
import com.example.Alkemy.Disney.models.PeliculaPersonaje;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PeliculaDTO {

    private long id;

    private String imagen;

    private String titulo;

    private LocalDate fecha;

    private int calificacion;

    private Set<PeliculaPersonajeDTO> Personajes = new HashSet<>();

    private GeneroDTO genero;

    public PeliculaDTO() {
    }

    public PeliculaDTO(Pelicula pelicula){
        this.id = pelicula.getId();
        this.imagen = pelicula.getImagen();
        this.titulo = pelicula.getTitulo();
        this.fecha = pelicula.getFecha();
        this.calificacion = pelicula.getCalificacion();
        this.Personajes = pelicula.getPeliculaPersonajes().stream().map(peliculaPersonaje -> new PeliculaPersonajeDTO(peliculaPersonaje)).collect(Collectors.toSet());
        this.genero = new GeneroDTO(pelicula.getGenero());
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

    public GeneroDTO getGenero() {
        return genero;
    }
    public void setGenero(GeneroDTO genero) {
        this.genero = genero;
    }

    public Set<PeliculaPersonajeDTO> getPersonajes() {
        return Personajes;
    }
    public void setPersonajes(Set<PeliculaPersonajeDTO> personajes) {
        Personajes = personajes;
    }
}
