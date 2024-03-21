package com.zeustesta.apirest.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zeustesta.apirest.Auth.AuthResponse;
import com.zeustesta.apirest.Auth.LoginRequest;
import com.zeustesta.apirest.Auth.RegisterRequest;
import com.zeustesta.apirest.Client.Client;
import com.zeustesta.apirest.Client.ClientRepository;
import com.zeustesta.apirest.Client.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final JwtService jwtService;
  private final ClientRepository cliRep;
  private final PasswordEncoder passwordEncoder;

  public AuthResponse login(LoginRequest login) {
    return null;
  }

  @SuppressWarnings("null")
  public AuthResponse register(RegisterRequest registerRequest) {
    Client newClient = Client.builder()
      .email(registerRequest.getEmail())
      .password(passwordEncoder.encode(registerRequest.getPassword()))
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
