package com.example.Alkemy.Disney.controllers;

import com.example.Alkemy.Disney.Servicios.PeliculaPersonajeServicio;
import com.example.Alkemy.Disney.Servicios.PersonajeServicio;
import com.example.Alkemy.Disney.dtos.PeliculaPersonajeDTO;
import com.example.Alkemy.Disney.dtos.PersonajeDTO;
import com.example.Alkemy.Disney.models.Personaje;
import com.example.Alkemy.Disney.repositories.PeliculaPersonajeRepository;
import com.example.Alkemy.Disney.repositories.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/characters")
public class PersonajeController {


    @Autowired
    private PersonajeServicio personajeServicio;

    @Autowired
    private PeliculaPersonajeServicio peliculaPersonajeServicio;

    @PostMapping("/crearPersonaje")
    public ResponseEntity<Object>  crear(@RequestParam String img,@RequestParam String nombre,
                                                  @RequestParam int edad, @RequestParam String peso,
                                                  @RequestParam String historia){
        if(img.isEmpty() || nombre.isEmpty() || peso.isEmpty() || historia.isEmpty() || edad <= 0 ){
            return new ResponseEntity<>("los campos no pueden estar vacios",HttpStatus.FORBIDDEN);
        }
        Personaje personaje = new Personaje(img,nombre,edad,peso,historia);
        personajeServicio.guardarPersonaje(personaje);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/editarPersonaje/{id}")
    public ResponseEntity<Object> editar(@PathVariable long id,@RequestParam String img,@RequestParam String nombre,
                                                  @RequestParam int edad, @RequestParam String peso,
                                                  @RequestParam String historia){
        Personaje personaje = personajeServicio.personajeById(id);
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
            personajeServicio.guardarPersonaje(personaje);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/eliminarPersonaje/{id}")
    ResponseEntity<Object> eliminar(@PathVariable long id){
        Personaje personaje = personajeServicio.personajeById(id);
        if(personaje == null){
            return new ResponseEntity<>("personaje no encontrado",HttpStatus.FORBIDDEN);
        }
        personajeServicio.eliminarPersonaje(personaje);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @GetMapping(params = "name")
    public List<PersonajeDTO> filtroname(@RequestParam String name){
        if(name.isEmpty()){
            return personajeServicio.getPersonajes();
        }
        return personajeServicio.getPersonajesByName(name);
    }

    @GetMapping(params = "age")
    public List<PersonajeDTO> filtroage(@RequestParam int age){
        return personajeServicio.getPersonajesByAge(age);
    }

    @GetMapping(params = "movies")
    public List<PeliculaPersonajeDTO> filtroid(@RequestParam long movies){
        return peliculaPersonajeServicio.personajeIdPelicula(movies);
    }

}


