package com.example.Alkemy.Disney.controllers;

import com.example.Alkemy.Disney.dtos.PeliculaDTO;
import com.example.Alkemy.Disney.dtos.PersonajeDTO;
import com.example.Alkemy.Disney.repositories.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PeliculaController {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @GetMapping("/pelicula")
    public List<PeliculaDTO> getPeliculas(){
        return peliculaRepository.findAll().stream().map(pelicula -> new PeliculaDTO(pelicula)).collect(Collectors.toList());
    }
}
