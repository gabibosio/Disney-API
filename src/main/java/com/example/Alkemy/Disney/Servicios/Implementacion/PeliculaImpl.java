package com.example.Alkemy.Disney.Servicios.Implementacion;

import com.example.Alkemy.Disney.Servicios.PeliculaServicio;
import com.example.Alkemy.Disney.dtos.PeliculaDTO;
import com.example.Alkemy.Disney.models.Pelicula;
import com.example.Alkemy.Disney.models.Personaje;
import com.example.Alkemy.Disney.repositories.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeliculaImpl implements PeliculaServicio {

    @Autowired
    private PeliculaRepository peliculaRepository;
    @Override
    public void guardarPelicula(Pelicula pelicula) {
        peliculaRepository.save(pelicula);
    }

    @Override
    public Pelicula peliculaById(long id) {
        return peliculaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarPelicula(Pelicula pelicula) {
        peliculaRepository.delete(pelicula);
    }

    @Override
    public List<PeliculaDTO> getPeliculas() {
        return peliculaRepository.findAll().stream().map(pelicula1 -> new PeliculaDTO(pelicula1)).collect(Collectors.toList());
    }

    @Override
    public List<PeliculaDTO> getPeliculasByNombre(String name) {
        return peliculaRepository.findAll().stream().filter(pelicula -> pelicula.getTitulo().equals(name)).map(pelicula -> new PeliculaDTO(pelicula)).collect(Collectors.toList());
    }

    @Override
    public List<PeliculaDTO> getPeliculasByGenero(long genre) {
        return peliculaRepository.findAll().stream().filter(pelicula -> pelicula.getGenero().getId() == genre).map(pelicula -> new PeliculaDTO(pelicula)).collect(Collectors.toList());
    }
}
