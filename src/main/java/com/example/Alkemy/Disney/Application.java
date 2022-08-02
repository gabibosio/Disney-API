package com.example.Alkemy.Disney;

import com.example.Alkemy.Disney.models.*;
import com.example.Alkemy.Disney.models.Character;
import com.example.Alkemy.Disney.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner initData(CharacterRepository characterRepository,
									  MovieRepository movieRepository,
									  MovieCharacterRepository movieCharacterRepository, GenderRepository genderRepository,
									  ClientRepository clientRepository) {
		return (args) -> {

			Client user = new Client("gabriel","bosio","gabriel@gmail.com", passwordEncoder.encode("asd123"));
			clientRepository.save(user);
			Client admin = new Client("admin","bosio","admin@admin.com", passwordEncoder.encode("asd123"));
			clientRepository.save(admin);

			Character character1 = new Character("","Harry",20,"principal","");
			characterRepository.save(character1);
			Character character2 = new Character("","Ron",20,"principal","");
			characterRepository.save(character2);

			Character character3 = new Character("","Gandalf",50,"principal","");
			characterRepository.save(character3);
			Character character4 = new Character("","Legolas",30,"principal","");
			characterRepository.save(character4);

			Gender gender1 = new Gender("drama","");
			genderRepository.save(gender1);

			Gender gender2 = new Gender("terror","");
			genderRepository.save(gender2);

			Movie movie1 = new Movie("","Harry Potter",LocalDate.now(),5, gender1);
			movieRepository.save(movie1);


			Movie movie2 = new Movie("","el señor de los anillos",LocalDate.now().plusDays(5),5, gender1);
			movieRepository.save(movie2);

			MovieCharacter movieCharacter = new MovieCharacter(character1, movie1);
			movieCharacterRepository.save(movieCharacter);

			MovieCharacter movieCharacter2 = new MovieCharacter(character2, movie1);
			movieCharacterRepository.save(movieCharacter2);

			MovieCharacter movieCharacter3 = new MovieCharacter(character3, movie2);
			movieCharacterRepository.save(movieCharacter3);

			MovieCharacter movieCharacter4 = new MovieCharacter(character4, movie2);
			movieCharacterRepository.save(movieCharacter4);

		};
	}
}


