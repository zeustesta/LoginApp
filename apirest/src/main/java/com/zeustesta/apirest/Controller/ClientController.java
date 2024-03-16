package com.zeustesta.apirest.Controller;

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
@RequestMapping
@RequiredArgsConstructor
public class ClientController {
  private final ClientService clientServ;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/client/createClient")
  public ResponseEntity<String> createClient(@RequestBody Client newClient) {
    clientServ.createClient(newClient);
    return ResponseEntity.ok("Client created successfully");
  }

  @GetMapping("/client/getClientById/{clientId}")
  public Client findById(@PathVariable UUID clientId) {
    Client founded = clientServ.findById(clientId);
    return founded;
  }

  @GetMapping("/client/getClientByEmail/{clientEmail}")
  public Client findByEmail(@PathVariable String clientEmail) {
    Client founded = clientServ.findByEmail(clientEmail);
    return founded;
  }

  @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
  @DeleteMapping("/client/deleteClientByEmail/{clientId}")
  public ResponseEntity<String> createClient(@PathVariable UUID clientId) {
    clientServ.deleteClient(clientId);
    return ResponseEntity.ok("Client deleted successfully");
  }
}
