package com.lcd.jwt.api.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignupException extends RuntimeException {

  public static final String USERNAME_ALREADY_EXISTS = "signup-username-already-exists";
  public static final String EMAIL_ALREADY_EXISTS = "signup-email-already-exists";

  private Object argument;
  private String format;
}
