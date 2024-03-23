package com.zeustesta.apirest.Client;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController {
  private final ClientService clientServ;

  @GetMapping(value = "{id}")
  public ResponseEntity<ClientResponse> getUser(@PathVariable UUID idBuscado) {
    ClientDTO clientDTO = clientServ.findById(idBuscado);
    if (clientDTO == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(clientDTO);
  }

  @PutMapping()
  public ResponseEntity<ClientResponse> updateClient(@RequestBody ClientRequest clientRequest) {
    return ResponseEntity.ok(clientServ.updateClient(clientRequest));
  }
}
