package com.example.Alkemy.Disney.Servicios.Implementacion;

import com.example.Alkemy.Disney.Servicios.UsuarioServicio;
import com.example.Alkemy.Disney.dtos.UsuarioDTO;
import com.example.Alkemy.Disney.models.Usuario;
import com.example.Alkemy.Disney.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioImpl implements UsuarioServicio {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public List<UsuarioDTO> getusuarios() {
        return usuarioRepository.findAll().stream().map(usuario -> new UsuarioDTO(usuario)).collect(Collectors.toList());
    }
}
