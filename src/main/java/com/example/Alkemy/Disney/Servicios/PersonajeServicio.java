package com.example.Alkemy.Disney.Servicios;

import com.example.Alkemy.Disney.dtos.PersonajeDTO;
import com.example.Alkemy.Disney.models.Personaje;

import java.util.List;
import java.util.Set;

public interface PersonajeServicio {
    void guardarPersonaje(Personaje personaje);

    Personaje personajeById(long id);

    void eliminarPersonaje(Personaje personaje);

    List<Personaje> personajes(Set<Long> id);

    List<PersonajeDTO> getPersonajes();

    List<PersonajeDTO> getPersonajesByName(String name);

    List<PersonajeDTO> getPersonajesByAge(int age);
}
