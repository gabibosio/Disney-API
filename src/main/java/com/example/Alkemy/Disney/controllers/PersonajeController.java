package com.example.Alkemy.Disney.controllers;

import com.example.Alkemy.Disney.dtos.PersonajeDTO;
import com.example.Alkemy.Disney.models.Personaje;
import com.example.Alkemy.Disney.repositories.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonajeController {

    @Autowired
    private PersonajeRepository personajeRepository;

    @PostMapping("/crearPersonaje")
    public ResponseEntity<Object>  crear(@RequestParam String img,@RequestParam String nombre,
                                                  @RequestParam int edad, @RequestParam String peso,
                                                  @RequestParam String historia){
        if(img.isEmpty() || nombre.isEmpty() || peso.isEmpty() || historia.isEmpty() || edad <= 0 ){
            return new ResponseEntity<>("los campos no pueden estar vacios",HttpStatus.FORBIDDEN);
        }
        Personaje personaje = new Personaje(img,nombre,edad,peso,historia);
        personajeRepository.save(personaje);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/editarPersonaje/{id}")
    public ResponseEntity<Object> editar(@PathVariable long id,@RequestParam String img,@RequestParam String nombre,
                                                  @RequestParam int edad, @RequestParam String peso,
                                                  @RequestParam String historia){
        Personaje personaje = personajeRepository.findById(id).orElse(null);
        if(img.isEmpty() || nombre.isEmpty() || peso.isEmpty() || historia.isEmpty() || edad <= 0 ){
            return new ResponseEntity<>("los campos no pueden estar vacios",HttpStatus.FORBIDDEN);
        }
        if(personaje == null){
            return new ResponseEntity<>("personaje no encontrado",HttpStatus.FORBIDDEN);
        }

            personaje.setImagen(img);
            personaje.setNombre(nombre);
            personaje.setEdad(edad);
            personaje.setPeso(peso);
            personaje.setHistoria(historia);
            personajeRepository.save(personaje);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/eliminarPersonaje/{id}")
    ResponseEntity<Object> eliminar(@PathVariable long id){
        Personaje personaje = personajeRepository.findById(id).orElse(null);
        if(personaje == null){
            return new ResponseEntity<>("personaje no encontrado",HttpStatus.FORBIDDEN);
        }
        personajeRepository.delete(personaje);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}

