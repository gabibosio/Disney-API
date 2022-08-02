package com.example.Alkemy.Disney.dtos;

import com.example.Alkemy.Disney.models.Gender;

public class GenderDTO {

    private long id;

    private String name;

    private String image;

    public GenderDTO() {
    }

    public GenderDTO(Gender gender) {
        this.id = gender.getId();
        this.name = gender.getName();
        this.image = gender.getImage();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
