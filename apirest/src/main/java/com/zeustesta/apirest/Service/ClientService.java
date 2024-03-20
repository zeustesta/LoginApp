package com.zeustesta.apirest.Service;

import java.util.List;
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

  public Client findById(UUID userId) {
    Client found = clientRep.findByUserId(userId);
    if (found != null) {
      return found;
    } else {
      return null;
    }
  }

  public Client findByEmail(String userEmail) {
    Client found = clientRep.findByEmail(userEmail);
    if (found != null) {
      return found;
    } else {
      return null;
    }
  }
}
