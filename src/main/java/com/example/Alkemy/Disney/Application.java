package com.example.Alkemy.Disney;

import com.example.Alkemy.Disney.models.Genero;
import com.example.Alkemy.Disney.models.Pelicula;
import com.example.Alkemy.Disney.models.PeliculaPersonaje;
import com.example.Alkemy.Disney.models.Personaje;
import com.example.Alkemy.Disney.repositories.GeneroRepository;
import com.example.Alkemy.Disney.repositories.PeliculaPersonajeRepository;
import com.example.Alkemy.Disney.repositories.PeliculaRepository;
import com.example.Alkemy.Disney.repositories.PersonajeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner initData(PersonajeRepository personajeRepository,
									  PeliculaRepository peliculaRepository,
									  PeliculaPersonajeRepository peliculaPersonajeRepository, GeneroRepository generoRepository) {
		return (args) -> {

			Personaje personaje1 = new Personaje("","harry",20,"principal","");
			personajeRepository.save(personaje1);
			Personaje personaje2 = new Personaje("","Ron",20,"principal","");
			personajeRepository.save(personaje2);

			Genero genero1 = new Genero("drama","");
			generoRepository.save(genero1);

			Genero genero2 = new Genero("terror","");
			generoRepository.save(genero2);

			Pelicula pelicula1 = new Pelicula("asd","Harry Potter",LocalDate.now(),1,genero1);
			peliculaRepository.save(pelicula1);


			Pelicula pelicula2 = new Pelicula("asd","el senor de los anillos",LocalDate.now(),1,genero1);
			peliculaRepository.save(pelicula2);

			PeliculaPersonaje peliculaPersonaje = new PeliculaPersonaje(personaje1,pelicula1);
			peliculaPersonajeRepository.save(peliculaPersonaje);

			PeliculaPersonaje peliculaPersonaje2 = new PeliculaPersonaje(personaje2,pelicula1);
			peliculaPersonajeRepository.save(peliculaPersonaje2);

		};
	}
}


