package com.example.Alkemy.Disney.Servicios.Implementacion;

import com.example.Alkemy.Disney.Servicios.PeliculaPersonajeServicio;
import com.example.Alkemy.Disney.models.PeliculaPersonaje;
import com.example.Alkemy.Disney.repositories.PeliculaPersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
