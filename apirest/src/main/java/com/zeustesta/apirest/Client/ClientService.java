package com.zeustesta.apirest.Client;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {
  private final ClientRepository clientRep;

  @Transactional
  public ClientResponse updateClient(ClientRequest clientRequest) {
    Client client = Client.builder()
      .userId(clientRequest.getUserId())
      .email(clientRequest.getEmail())
      .firstName(clientRequest.getFirstName())
      .lastName(clientRequest.getLastName())
      .role(Role.USER)
      .build();
    
    clientRep.updateClient(client.getUserId(), client.getFirstName(), client.getLastName());

    return new ClientResponse("Usuario registrado correctamente");
  }
}
