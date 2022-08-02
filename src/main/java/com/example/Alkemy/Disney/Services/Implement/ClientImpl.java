package com.example.Alkemy.Disney.Services.Implement;

import com.example.Alkemy.Disney.Services.ClientService;
import com.example.Alkemy.Disney.dtos.ClientDTO;
import com.example.Alkemy.Disney.models.Client;
import com.example.Alkemy.Disney.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void saveUser(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public List<ClientDTO> getUsers() {
        return clientRepository.findAll().stream().map(users -> new ClientDTO(users)).collect(Collectors.toList());
    }
}
