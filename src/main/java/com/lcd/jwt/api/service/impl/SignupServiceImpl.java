package com.lcd.jwt.api.service.impl;

import com.lcd.jwt.api.dto.AuthResponseDTO;
import com.lcd.jwt.api.ei.SignupEI;
import com.lcd.jwt.api.entity.User;
import com.lcd.jwt.api.exception.SignupException;
import com.lcd.jwt.api.repository.UserRepository;
import com.lcd.jwt.api.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SignupServiceImpl implements SignupService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Object signup(SignupEI ei) {

    if (userRepository.existsByEmail(ei.getEmail())) {

      throw SignupException.builder()
          .argument(ei.getEmail())
          .format(SignupException.EMAIL_ALREADY_EXISTS)
          .build();

    } else if (userRepository.existsByUsername(ei.getUsername())) {

      throw SignupException.builder()
          .argument(ei.getUsername())
          .format(SignupException.USERNAME_ALREADY_EXISTS)
          .build();

    }

    User user = User.builder()
        .username(ei.getUsername())
        .email(ei.getEmail())
        .password(passwordEncoder.encode(ei.getPassword()))
        .build();

    userRepository.save(user);

    return AuthResponseDTO.builder()
        .id(user.getId())
        .build();

  }

}
