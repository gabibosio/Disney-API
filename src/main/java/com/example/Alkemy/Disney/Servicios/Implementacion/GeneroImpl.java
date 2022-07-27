package com.example.Alkemy.Disney.Servicios.Implementacion;

import com.example.Alkemy.Disney.Servicios.GeneroServicio;
import com.example.Alkemy.Disney.models.Genero;
import com.example.Alkemy.Disney.repositories.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroImpl implements GeneroServicio {

    @Autowired
    private GeneroRepository generoRepository;

    @Override
    public Genero generoById(long id) {
        return generoRepository.findById(id).orElse(null);
    }
}
