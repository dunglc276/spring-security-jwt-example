package com.lcd.jwt.api.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InvalidGrantException extends RuntimeException{

  public static final String ERROR = "error";
  public static final String DESCRIPTION = "error_description";
  public static final String STATUS = "status";
  public static final String TIME_ERROR = "timestamp";
  public static final String INVALID_GRANT = "invalid_grant";
  public static final String INVALID_TOKEN = "invalid_token";

  private String error;
  private String errorDescription;
}
