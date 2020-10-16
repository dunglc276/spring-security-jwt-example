package com.lcd.jwt.api.controller;

import com.lcd.jwt.api.ei.AuthUserEI;
import com.lcd.jwt.api.ei.SignupEI;
import com.lcd.jwt.api.service.SignupService;
import com.lcd.jwt.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WelcomeController {

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private SignupService signupService;

  @GetMapping
  public String welcome() {
    return "Welcome to lcd!!";
  }

  @PostMapping("/auth/token")
  public ResponseEntity<?> signIn(@RequestBody AuthUserEI ei) throws Exception {

    try{
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(ei.getUsername(), ei.getPassword()));

    } catch (Exception ex) {

      throw new Exception("invalid username/password");
    }

    return ResponseEntity.ok(jwtUtil.generateToken(ei.getUsername()));
  };

  @PostMapping("/signup")
  public ResponseEntity<?> signup(@RequestBody SignupEI ei) throws Exception {

    return ResponseEntity.ok(signupService.signup(ei));
  };
}
