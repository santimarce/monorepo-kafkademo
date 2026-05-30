package com.microkafka.clients.service.impl;

import com.microkafka.clients.dto.ClientDto;
import com.microkafka.clients.model.Client;
import com.microkafka.clients.repository.ClientRepository;
import com.microkafka.clients.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client create(ClientDto clientDto) {
        Client client = Client.builder()
                .name(clientDto.name())
                .email(clientDto.email())
                .build();

        return clientRepository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
