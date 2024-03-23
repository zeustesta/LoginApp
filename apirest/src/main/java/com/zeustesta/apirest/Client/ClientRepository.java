package com.zeustesta.apirest.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, UUID>{
  Optional<Client> findByEmail(String email);
  @Modifying()
  @Query("update Client c set c.first_name=:firstName, c.last_name=:lastName where c.USER_ID = id")
  void updateClient(@Param(value = "id") UUID id, 
                    @Param(value = "firstName") String firstName, 
                    @Param(value = "lastName") String lastName
  );
}
