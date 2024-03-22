package com.zeustesta.apirest.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
  private final AuthenticationManager authManager;

  public AuthResponse login(LoginRequest login) {
    authManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
    UserDetails client = cliRep.findByEmail(login.getEmail()).orElseThrow();
    String token = jwtService.getToken(client);

    return AuthResponse.builder()
      .token(token)
      .build(); 
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
