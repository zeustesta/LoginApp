package com.zeustesta.apirest.Client;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, UUID>{
  Client findByUserId(UUID userId);
  Client findByEmail(String email);
  List<Client> findAll();
}
