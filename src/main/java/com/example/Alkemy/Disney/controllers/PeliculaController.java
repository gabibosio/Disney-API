package com.example.Alkemy.Disney.controllers;

import com.example.Alkemy.Disney.Servicios.GeneroServicio;
import com.example.Alkemy.Disney.Servicios.PeliculaPersonajeServicio;
import com.example.Alkemy.Disney.Servicios.PeliculaServicio;
import com.example.Alkemy.Disney.Servicios.PersonajeServicio;
import com.example.Alkemy.Disney.dtos.PeliculaDTO;
import com.example.Alkemy.Disney.models.Genero;
import com.example.Alkemy.Disney.models.Pelicula;
import com.example.Alkemy.Disney.models.PeliculaPersonaje;
import com.example.Alkemy.Disney.models.Personaje;
import com.example.Alkemy.Disney.repositories.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class PeliculaController {


    @Autowired
    private PersonajeServicio personajeServicio;

    @Autowired
    private GeneroServicio generoServicio;

    @Autowired
    private PeliculaServicio peliculaServicio;

    @Autowired
    private PeliculaPersonajeServicio peliculaPersonajeServicio;


    @GetMapping("/pelicula")
    public List<PeliculaDTO> getPeliculas(){
        return peliculaServicio.getPeliculas();
    }


    @PostMapping("/crearPelicula")
    public ResponseEntity<Object> crear(@RequestParam String img, @RequestParam String titulo,
                                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha,
                                        @RequestParam int calificacion, @RequestParam long idGenero,
                                        @RequestParam Set<Long> idPersonajes){

        if(img.isEmpty() || titulo.isEmpty() || calificacion <=0){
            return new ResponseEntity<>("los campos no pueden estar vacios",HttpStatus.FORBIDDEN);
        }

        List <Personaje> personaje = personajeServicio.personajes(idPersonajes);
        Genero genero = generoServicio.generoById(idGenero);

        Pelicula pelicula = new Pelicula(img,titulo,fecha,calificacion,genero);
        peliculaServicio.guardarPelicula(pelicula);
        List<PeliculaPersonaje> peliculaPersonajes = personaje.stream().map(personaje1 -> new PeliculaPersonaje(personaje1,pelicula)).collect(Collectors.toList());
        peliculaPersonajes.forEach(peliculaPersonaje -> peliculaPersonajeServicio.guardarPeliculaPersonaje(peliculaPersonaje));


        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PatchMapping("/editarPelicula/{id}")
    public ResponseEntity<Object> editar(@PathVariable long id,@RequestParam String img, @RequestParam String titulo,
                                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha,
                                         @RequestParam int calificacion,@RequestParam long idGenero){
        if(img.isEmpty() || titulo.isEmpty() || calificacion <=0){
            return new ResponseEntity<>("los campos no pueden estar vacios",HttpStatus.FORBIDDEN);
        }

        Pelicula pelicula = peliculaServicio.peliculaById(id);
        if(pelicula == null){
            return new ResponseEntity<>("la pelicula no existe",HttpStatus.FORBIDDEN);
        }
        Genero genero = generoServicio.generoById(idGenero);

        pelicula.setImagen(img);
        pelicula.setTitulo(titulo);
        pelicula.setFecha(fecha);
        pelicula.setCalificacion(calificacion);
        pelicula.setGenero(genero);
        peliculaServicio.guardarPelicula(pelicula);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/eliminarPelicula/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable long id){
        Pelicula pelicula = peliculaServicio.peliculaById(id);
        if(pelicula == null){
            return new ResponseEntity<>("la pelicula no existe",HttpStatus.FORBIDDEN);
        }

        Set<PeliculaPersonaje> personajes = pelicula.getPeliculaPersonajes();
        personajes.forEach(peliculaPersonaje -> peliculaPersonajeServicio.eliminarPeliculaPersonaje(peliculaPersonaje));
        peliculaServicio.eliminarPelicula(pelicula);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Object> agregarPersonaje (@PathVariable long idMovie,
                                                    @PathVariable long idCharacter){
        Pelicula pelicula = peliculaServicio.peliculaById(idMovie);
        if(pelicula == null){
            return new ResponseEntity<>("la pelicula no existe",HttpStatus.FORBIDDEN);
        }
        Personaje personaje = personajeServicio.personajeById(idCharacter);
        if(personaje == null){
            return new ResponseEntity<>("el personaje no existe",HttpStatus.FORBIDDEN);
        }

        PeliculaPersonaje peliculaPersonaje = new PeliculaPersonaje(personaje,pelicula);
        Set<PeliculaPersonaje> peliculaPersonajes= pelicula.getPeliculaPersonajes();
        peliculaPersonajes.add(peliculaPersonaje);
        peliculaPersonajeServicio.guardarPeliculaPersonaje(peliculaPersonaje);


        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Object> eliminarPersonaje (@PathVariable long idMovie,
                                                    @PathVariable long idCharacter){
        Pelicula pelicula = peliculaServicio.peliculaById(idMovie);
        if(pelicula == null){
            return new ResponseEntity<>("la pelicula no existe",HttpStatus.FORBIDDEN);
        }

        Personaje personaje = personajeServicio.personajeById(idCharacter);
        if(personaje == null){
            return new ResponseEntity<>("el personaje no existe",HttpStatus.FORBIDDEN);
        }


        Set<PeliculaPersonaje> peliculaPersonajes = pelicula.getPeliculaPersonajes();
        List<PeliculaPersonaje> peliculaPersonaje = peliculaPersonajes.stream().filter(peliculaPersonaje1 ->  peliculaPersonaje1.getPersonaje().getId() == idCharacter).collect(Collectors.toList());
        peliculaPersonaje.forEach(peliculaPersonaje2 -> peliculaPersonajeServicio.eliminarPeliculaPersonaje(peliculaPersonaje2));

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(params = "name")
    public List<PeliculaDTO> filtroNombre(@RequestParam String name){
        if(name.isEmpty()){
            return peliculaServicio.getPeliculas();
        }
        return peliculaServicio.getPeliculasByNombre(name);
    }

    @GetMapping(params = "genre")
    public List<PeliculaDTO> filtroGenero(@RequestParam long genre){
        return peliculaServicio.getPeliculasByGenero(genre);
    }

    @GetMapping(params = "order")
    public List<PeliculaDTO> filtroOrdenado(@RequestParam String order){

        if(order.equals("ASC")){
            List<PeliculaDTO> peliculas = peliculaServicio.getPeliculas();
            peliculas.sort((p1, p2) -> p1.getFecha().compareTo(p2.getFecha()));
            return peliculas;
        }
        if(order.equals("DESC")){
            List<PeliculaDTO> peliculas = peliculaServicio.getPeliculas();
            peliculas.sort((p1, p2) -> p2.getFecha().compareTo(p1.getFecha()));
            return peliculas;
        }

        if(order.isEmpty()){
            List<PeliculaDTO> peliculas = peliculaServicio.getPeliculas();
            return peliculas;
        }

        return null;
    }
}
