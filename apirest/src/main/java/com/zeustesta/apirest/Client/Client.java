package com.zeustesta.apirest.Client;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "USER_ID")
  private UUID userId;
  private String firstName;
  private String lastName;
  @Column(name = "USER_EMAIL")
  private String email;
  @Column(name = "USER_PASSWORD")
  private String password;
  @Column(name = "USER_ROLE")
  @Enumerated(EnumType.STRING)
  private Role role;
}
