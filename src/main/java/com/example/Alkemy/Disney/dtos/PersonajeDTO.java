package com.example.Alkemy.Disney.dtos;

import com.example.Alkemy.Disney.models.Personaje;

public class PersonajeDTO {

    private long id;

    private String imagen;

    private String nombre;

    private int edad;

    private String peso;

    private String historia;

    public PersonajeDTO() {
    }

    public PersonajeDTO(Personaje personaje) {
        this.id = personaje.getId();
        this.imagen = personaje.getImagen();
        this.nombre = personaje.getNombre();
        this.edad = personaje.getEdad();
        this.peso = personaje.getPeso();
        this.historia = personaje.getHistoria();
    }

    public long getId() {
        return id;
    }


    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPeso() {
        return peso;
    }
    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getHistoria() {
        return historia;
    }
    public void setHistoria(String historia) {
        this.historia = historia;
    }
}
