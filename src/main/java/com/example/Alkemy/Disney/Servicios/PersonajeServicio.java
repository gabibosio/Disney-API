package com.example.Alkemy.Disney.Servicios;

import com.example.Alkemy.Disney.models.Personaje;

import java.util.List;
import java.util.Set;

public interface PersonajeServicio {
    void guardarPersonaje(Personaje personaje);

    Personaje personajeById(long id);

    void eliminarPersonaje(Personaje personaje);

    List<Personaje> personajes(Set<Long> id);
}
