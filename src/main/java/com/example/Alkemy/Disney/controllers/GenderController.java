package com.example.Alkemy.Disney.controllers;

import com.example.Alkemy.Disney.Services.GenderService;
import com.example.Alkemy.Disney.models.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenderController {

    @Autowired
    private GenderService genderService;

    @PostMapping("/createGender")
    ResponseEntity<Object> create (@RequestParam String name, @RequestParam String image){
        if(name.isEmpty() || image.isEmpty()){
            return new ResponseEntity<>("Missing Data",HttpStatus.FORBIDDEN);
        }
        Gender gender = new Gender(name,image);
        genderService.saveGender(gender);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
