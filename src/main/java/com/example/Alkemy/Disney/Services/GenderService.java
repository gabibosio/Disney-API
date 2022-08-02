package com.example.Alkemy.Disney.Services;

import com.example.Alkemy.Disney.models.Gender;

public interface GenderService {

    Gender genderById(long id);

    void saveGender(Gender gender);
}
