package com.example.Alkemy.Disney.Services.Implement;

import com.example.Alkemy.Disney.Services.GenderService;
import com.example.Alkemy.Disney.models.Gender;
import com.example.Alkemy.Disney.repositories.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderImpl implements GenderService {

    @Autowired
    private GenderRepository genderRepository;

    @Override
    public Gender genderById(long id) {
        return genderRepository.findById(id).orElse(null);
    }

    @Override
    public void saveGender(Gender gender) {
        genderRepository.save(gender);
    }
}
