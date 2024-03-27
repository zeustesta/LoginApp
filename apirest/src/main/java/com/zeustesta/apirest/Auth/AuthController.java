package com.zeustesta.apirest.Auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {

  private final AuthService authService;
  
  @PostMapping(value = "login")
  public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok(authService.login(loginRequest));
  }
  
  @PostMapping(value = "register")
  public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
    return ResponseEntity.ok(authService.register(registerRequest));
  }
}
