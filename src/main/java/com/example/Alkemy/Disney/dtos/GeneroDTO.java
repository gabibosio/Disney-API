package com.example.Alkemy.Disney.dtos;

import com.example.Alkemy.Disney.models.Genero;

public class GeneroDTO {

    private long id;

    private String nombre;

    private String imagen;

    public GeneroDTO() {
    }

    public GeneroDTO(Genero genero) {
        this.id = genero.getId();
        this.nombre = genero.getNombre();
        this.imagen = genero.getImagen();
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
