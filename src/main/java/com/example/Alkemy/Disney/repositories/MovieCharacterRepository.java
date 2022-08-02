package com.example.Alkemy.Disney.repositories;

import com.example.Alkemy.Disney.models.MovieCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MovieCharacterRepository extends JpaRepository<MovieCharacter,Long> {
}
