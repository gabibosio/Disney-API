package com.example.Alkemy.Disney.Services;

import com.example.Alkemy.Disney.dtos.ClientDTO;
import com.example.Alkemy.Disney.models.Client;

import java.util.List;

public interface ClientService {

    void saveUser(Client client);

    Client findByEmail(String email);

    List<ClientDTO> getUsers();
}
