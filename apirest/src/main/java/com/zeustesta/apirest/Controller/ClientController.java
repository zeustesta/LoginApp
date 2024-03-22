package com.zeustesta.apirest.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zeustesta.apirest.Client.Client;
import com.zeustesta.apirest.Service.ClientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
  private final ClientService clientServ;

  @ResponseStatus(HttpStatus.FOUND)
  @GetMapping("/getClients")
  public List<Client> findAll() {
    return clientServ.findAll();
  }

  @ResponseStatus(HttpStatus.FOUND)
  @GetMapping("/getClientById/{clientId}")
  public Optional<Client> findById(@PathVariable UUID clientId) {
    return clientServ.findById(clientId);
  }

  @ResponseStatus(HttpStatus.FOUND)
  @GetMapping("/getClientByEmail/{clientEmail}")
  public Optional<Client> findByEmail(@PathVariable String clientEmail) {
    return clientServ.findByEmail(clientEmail);
  }

  @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
  @DeleteMapping("/deleteClientById/{clientId}")
  public ResponseEntity<String> createClient(@PathVariable UUID clientId) {
    clientServ.deleteClient(clientId);
    return ResponseEntity.ok("Client deleted successfully");
  }
}
