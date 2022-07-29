package com.example.Alkemy.Disney.Servicios;

import com.example.Alkemy.Disney.dtos.PeliculaDTO;
import com.example.Alkemy.Disney.models.Pelicula;
import com.example.Alkemy.Disney.models.Personaje;

import java.util.List;

public interface PeliculaServicio {

    void guardarPelicula(Pelicula pelicula);

    Pelicula peliculaById(long id);

    void eliminarPelicula(Pelicula pelicula);

    List<PeliculaDTO> getPeliculas();

    List<PeliculaDTO> getPeliculasByNombre(String name);

    List<PeliculaDTO> getPeliculasByGenero(long genre);
}
