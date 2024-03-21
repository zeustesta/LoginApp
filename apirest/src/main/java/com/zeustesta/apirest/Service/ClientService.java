package com.zeustesta.apirest.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.zeustesta.apirest.Client.Client;
import com.zeustesta.apirest.Client.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {
  private final ClientRepository clientRep;

  public void createClient(Client newClient) {
    if (newClient != null) {
      clientRep.save(newClient);
    }
  }

  public void deleteClient(UUID userId) {
    if (userId != null) {
      clientRep.deleteById(userId);
    }
  }

  public List<Client> findAll() {
    return clientRep.findAll();
  }

  public Optional<Client> findById(UUID userId) {
    return clientRep.findByUserId(userId);
  }

  public Optional<Client> findByEmail(String userEmail) {
    return clientRep.findByEmail(userEmail);
  }
}
