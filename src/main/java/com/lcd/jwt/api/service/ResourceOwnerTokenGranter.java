package com.lcd.jwt.api.service;

import com.lcd.jwt.api.ei.AuthUserEI;
import com.lcd.jwt.api.exception.InvalidGrantException;
import com.lcd.jwt.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class ResourceOwnerTokenGranter {

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private AuthenticationManager authenticationManager;

  public Object login(AuthUserEI ei) {

    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(ei.getUsername(), ei.getPassword()));
      }
    catch (AuthenticationException ae) {
     throw InvalidGrantException.builder()
         .error(ae.getMessage())
         .build();
    }

  return jwtUtil.generateToken(ei.getUsername());
  }
}
