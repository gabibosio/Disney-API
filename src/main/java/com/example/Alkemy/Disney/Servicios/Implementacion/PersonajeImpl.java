package com.example.Alkemy.Disney.Servicios.Implementacion;

import com.example.Alkemy.Disney.Servicios.PersonajeServicio;
import com.example.Alkemy.Disney.dtos.PersonajeDTO;
import com.example.Alkemy.Disney.models.Personaje;
import com.example.Alkemy.Disney.repositories.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public List<PersonajeDTO> getPersonajes() {
        return personajeRepository.findAll().stream().map(personaje -> new PersonajeDTO(personaje)).collect(Collectors.toList());
    }

    @Override
    public List<PersonajeDTO> getPersonajesByName(String name) {
        return personajeRepository.findAll().stream().filter(personaje -> personaje.getNombre().equals(name)).map(personaje -> new PersonajeDTO(personaje)).collect(Collectors.toList());
    }

    @Override
    public List<PersonajeDTO> getPersonajesByAge(int age) {
        return personajeRepository.findAll().stream().filter(personaje -> personaje.getEdad() == age).map(personaje -> new PersonajeDTO(personaje)).collect(Collectors.toList());
    }


}
