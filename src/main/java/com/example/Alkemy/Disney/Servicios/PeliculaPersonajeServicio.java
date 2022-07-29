package com.example.Alkemy.Disney.Servicios;

import com.example.Alkemy.Disney.dtos.PeliculaPersonajeDTO;
import com.example.Alkemy.Disney.models.PeliculaPersonaje;

import java.util.List;

public interface PeliculaPersonajeServicio {

    void guardarPeliculaPersonaje(PeliculaPersonaje peliculaPersonaje);

    void eliminarPeliculaPersonaje(PeliculaPersonaje peliculaPersonaje);

    List<PeliculaPersonajeDTO> personajeIdPelicula(long id);
}
