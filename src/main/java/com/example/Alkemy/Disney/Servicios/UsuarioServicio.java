package com.example.Alkemy.Disney.Servicios;

import com.example.Alkemy.Disney.dtos.UsuarioDTO;
import com.example.Alkemy.Disney.models.Usuario;

import java.util.List;

public interface UsuarioServicio {

    void guardarUsuario(Usuario usuario);

    Usuario findByEmail(String email);

    List<UsuarioDTO> getusuarios();
}
