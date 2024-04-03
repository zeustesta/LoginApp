package com.zeustesta.apirest.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
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
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authManager;

  public LoginResponse login(LoginRequest login) {
    authManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
    Client clientInfo = cliRep.findByEmail(login.getEmail()).orElseThrow();
    String token = jwtService.getToken(clientInfo);

    return LoginResponse.builder()
      .token(token)
      .userId(clientInfo.getUserId())
      .build(); 
  }

  public RegisterResponse register(RegisterRequest registerRequest) {
    Client newClient = Client.builder()
      .email(registerRequest.getEmail())
      .password(passwordEncoder.encode(registerRequest.getPassword()))
      .firstName(registerRequest.getFirstName())
      .lastName(registerRequest.getLastName())
      .role(Role.USER)
      .build();

    cliRep.save(newClient);

    return RegisterResponse.builder()
      .message("Correctly registered")
      .build();
  }
}
