package com.ltnc.be.port.service;

import java.security.Key;
import org.springframework.security.core.Authentication;

public interface JwtService {
  Boolean validateToken(String token);

  String generateToken(Authentication authentication);

  String generateToken(String principal, Long jwtExpirationMs);

  String generateToken(String principal);

  Key generateSecretKey();

  String extractCredential(String token);

  String extractPrincipal(Authentication authentication);
}
