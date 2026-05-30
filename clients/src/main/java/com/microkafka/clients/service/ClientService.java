package com.microkafka.clients.service;

import com.microkafka.clients.dto.ClientDto;
import com.microkafka.clients.model.Client;

import java.util.List;

public interface ClientService {
    Client create(ClientDto clientDto);

    List<Client> findAll();
}
