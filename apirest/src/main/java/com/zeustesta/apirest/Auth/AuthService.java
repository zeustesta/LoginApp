package com.zeustesta.apirest.Auth;

import org.springframework.stereotype.Service;

import com.zeustesta.apirest.Client.Client;
import com.zeustesta.apirest.Client.ClientRepository;
import com.zeustesta.apirest.Client.Role;
import com.zeustesta.apirest.Jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final JwtService jwtService;
  private final ClientRepository cliRep;

  public AuthResponse login(LoginRequest login) {
    return null;
  }

  public AuthResponse register(RegisterRequest registerRequest) {
    Client newClient = Client.builder()
      .email(registerRequest.getEmail())
      .password(registerRequest.getPassword())
      .firstName(registerRequest.getFirstName())
      .lastName(registerRequest.getLastName())
      .role(Role.USER)
      .build();
    cliRep.save(newClient);
    return AuthResponse.builder()
      .token(jwtService.getToken(newClient))
      .build();
  }
}
