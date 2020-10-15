package com.lcd.jwt.api.controller;

import com.lcd.jwt.api.ei.AuthUserEI;
import com.lcd.jwt.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class WelcomeController {

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private AuthenticationManager authenticationManager;

  @GetMapping("/")
  public String welcome() {
    return "Welcome to lcd!!";
  }

  @PostMapping("/auth/token")
  public String generateToken(@RequestBody AuthUserEI ei) throws Exception {

    try{
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(ei.getUsername(), ei.getPassword()));

    } catch (Exception ex) {

      throw new Exception("invalid username/password");
    }

    return jwtUtil.generateToken(ei.getUsername());
  };
}
