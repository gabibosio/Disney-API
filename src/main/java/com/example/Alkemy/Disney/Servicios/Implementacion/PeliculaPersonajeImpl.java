package com.example.Alkemy.Disney.Servicios.Implementacion;

import com.example.Alkemy.Disney.Servicios.PeliculaPersonajeServicio;
import com.example.Alkemy.Disney.dtos.PeliculaPersonajeDTO;
import com.example.Alkemy.Disney.models.PeliculaPersonaje;
import com.example.Alkemy.Disney.repositories.PeliculaPersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeliculaPersonajeImpl implements PeliculaPersonajeServicio {
    @Autowired
    private PeliculaPersonajeRepository peliculaPersonajeRepository;
    @Override
    public void guardarPeliculaPersonaje(PeliculaPersonaje peliculaPersonaje) {
        peliculaPersonajeRepository.save(peliculaPersonaje);
    }

    @Override
    public void eliminarPeliculaPersonaje(PeliculaPersonaje peliculaPersonaje) {
        peliculaPersonajeRepository.delete(peliculaPersonaje);
    }

    @Override
    public List<PeliculaPersonajeDTO> personajeIdPelicula(long id) {
        return peliculaPersonajeRepository.findAll().stream().filter(peliculaPersonaje -> peliculaPersonaje.getPelicula().getId() == id).map(peliculaPersonaje -> new PeliculaPersonajeDTO(peliculaPersonaje)).collect(Collectors.toList());
    }
}
