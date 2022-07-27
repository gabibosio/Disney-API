package com.example.Alkemy.Disney.Servicios.Implementacion;

import com.example.Alkemy.Disney.Servicios.PersonajeServicio;
import com.example.Alkemy.Disney.models.Personaje;
import com.example.Alkemy.Disney.repositories.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PersonajeImpl implements PersonajeServicio {
    @Autowired
    private PersonajeRepository personajeRepository;

    @Override
    public void guardarPersonaje(Personaje personaje) {
        personajeRepository.save(personaje);
    }

    @Override
    public Personaje personajeById(long id) {
        return personajeRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarPersonaje(Personaje personaje) {
        personajeRepository.delete(personaje);
    }

    @Override
    public List<Personaje> personajes(Set<Long> id) {
        return personajeRepository.findAllById(id);
    }


}
