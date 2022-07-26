package com.example.Alkemy.Disney.dtos;

import com.example.Alkemy.Disney.models.Pelicula;
import com.example.Alkemy.Disney.models.PeliculaPersonaje;
import com.example.Alkemy.Disney.models.Personaje;

public class PeliculaPersonajeDTO {


    private PersonajeDTO personaje;


    public PeliculaPersonajeDTO() {
    }

    public PeliculaPersonajeDTO(PeliculaPersonaje peliculaPersonaje) {
        this.personaje = new PersonajeDTO(peliculaPersonaje.getPersonaje());
    }


    public PersonajeDTO getPersonaje() {
        return personaje;
    }
    public void setPersonaje(PersonajeDTO personaje) {
        this.personaje = personaje;
    }
}
