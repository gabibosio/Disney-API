package com.example.Alkemy.Disney.repositories;

import com.example.Alkemy.Disney.models.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GeneroRepository extends JpaRepository<Genero,Long> {
}
