package com.lcd.jwt.api.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

  private static final String SECRET = "lcd";
  private static final String encodedString = Base64.getEncoder().encodeToString(SECRET.getBytes());

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    Claims claims = Jwts.parser()
          .setSigningKey(encodedString)
          .parseClaimsJws(token)
          .getBody();

    return claimsResolver.apply(claims);
  }

  private Boolean isTokenExpired(String token) {
    Date expiration = extractClaim(token, Claims::getExpiration);

    return expiration.before(new Date());
  }

  public String generateToken(String subject) {
    Map<String, Object> claims = new HashMap<>();
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60)))
        .signWith(SignatureAlgorithm.HS512, encodedString)
        .compact();
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }
}
