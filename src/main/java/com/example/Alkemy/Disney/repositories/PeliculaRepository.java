package com.example.Alkemy.Disney.repositories;

import com.example.Alkemy.Disney.dtos.PeliculaDTO;
import com.example.Alkemy.Disney.models.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PeliculaRepository extends JpaRepository<Pelicula,Long> {
}
