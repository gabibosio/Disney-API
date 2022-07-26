package com.example.Alkemy.Disney.controllers;

import com.example.Alkemy.Disney.models.Genero;
import com.example.Alkemy.Disney.repositories.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneroController {

    @Autowired
    private GeneroRepository generoRepository;

    @PostMapping("/crearGenero")
    ResponseEntity<Object> crear(@RequestParam String nombre, @RequestParam String imagen){
        Genero genero = new Genero(nombre,imagen);
        generoRepository.save(genero);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
