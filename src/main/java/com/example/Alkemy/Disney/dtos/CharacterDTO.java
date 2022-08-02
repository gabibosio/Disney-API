package com.example.Alkemy.Disney.dtos;

import com.example.Alkemy.Disney.models.Character;

public class CharacterDTO {

    private long id;

    private String image;

    private String name;

    private int age;

    private String weight;

    private String history;

    public CharacterDTO() {
    }

    public CharacterDTO(Character character) {
        this.id = character.getId();
        this.image = character.getImage();
        this.name = character.getName();
        this.age = character.getAge();
        this.weight = character.getWeight();
        this.history = character.getHistory();
    }

    public long getId() {
        return id;
    }


    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHistory() {
        return history;
    }
    public void setHistory(String history) {
        this.history = history;
    }
}
