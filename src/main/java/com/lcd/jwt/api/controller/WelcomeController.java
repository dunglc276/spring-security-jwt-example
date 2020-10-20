package com.lcd.jwt.api.controller;

import com.lcd.jwt.api.ei.AuthUserEI;
import com.lcd.jwt.api.ei.SignupEI;
import com.lcd.jwt.api.service.ResourceOwnerTokenGranter;
import com.lcd.jwt.api.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WelcomeController {

  @Autowired
  private SignupService signupService;

  @Autowired
  private ResourceOwnerTokenGranter resourceOwnerTokenGranter;

  @GetMapping
  public String welcome() {
    return "Welcome to lcd!!";
  }

  @PostMapping("/auth/token")
  public ResponseEntity<?> signIn(@RequestBody AuthUserEI ei) throws Exception {

    return ResponseEntity.ok(resourceOwnerTokenGranter.login(ei));
  };

  @PostMapping("/signup")
  public ResponseEntity<?> signup(@RequestBody SignupEI ei) throws Exception {

    return ResponseEntity.ok(signupService.signup(ei));
  };
}
