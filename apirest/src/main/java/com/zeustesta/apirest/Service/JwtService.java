package com.zeustesta.apirest.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
  public static final long JWT_EXPIRATION_TOKEN = 300000;
  public static final String SECRET_KEY = "bd976708cf429dac62c15d98502baebcbdab93b0231b22aa9acee870a6da0e27";

  public String getToken(UserDetails userDetails) {
    return getToken(new HashMap<>(), userDetails);
  }

  public String getToken(Map<String, Object> extraClaims, UserDetails user) {
    return Jwts
      .builder()
      .setClaims(extraClaims)
      .setSubject(user.getUsername())
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TOKEN))
      .signWith(getKey())
      .compact();
  }

  public Key getKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
