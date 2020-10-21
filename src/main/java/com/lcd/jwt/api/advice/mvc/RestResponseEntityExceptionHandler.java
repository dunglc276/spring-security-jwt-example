package com.lcd.jwt.api.advice.mvc;

import com.lcd.jwt.api.dto.BadRequestDTO;
import com.lcd.jwt.api.exception.InvalidGrantException;
import com.lcd.jwt.api.exception.SignupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

    LOGGER.warn("Request '{}' responded status: '{}'", request.getDescription(false), HttpStatus.BAD_REQUEST);

    final BadRequestDTO badRequestDTO = BadRequestDTO.builder()
        .timestamps(OffsetDateTime.now())
        .message(ex.getMessage())
        .format("message.invalid.bad-request")
        .build();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badRequestDTO);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    LOGGER.warn("Request '{}' responded status: '{}'", request.getDescription(false), HttpStatus.BAD_REQUEST);

    final BadRequestDTO badRequestDTO = BadRequestDTO.builder()
        .timestamps(OffsetDateTime.now())
        .message(ex.getMessage())
        .format("message.invalid.bad-request")
        .build();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badRequestDTO);
  }

  @Override
  protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    LOGGER.warn("Request '{}' responded status: '{}'", request.getDescription(false), HttpStatus.BAD_REQUEST);

    final BadRequestDTO badRequestDTO = BadRequestDTO.builder()
        .timestamps(OffsetDateTime.now())
        .arguments(!StringUtils.isEmpty(ex.getFieldError()) ? Arrays.asList(ex.getFieldErrors()) : null)
        .format("message.invalid.bad-request")
        .build();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badRequestDTO);
  }

  @ExceptionHandler(value = SignupException.class)
  ResponseEntity<?> handleSignupException(SignupException exception, WebRequest request) {
    LOGGER.warn("Request '{}' responsed status: '{}'", request.getDescription(false), HttpStatus.BAD_REQUEST);

    BadRequestDTO badRequestDTO = BadRequestDTO.builder()
        .timestamps(OffsetDateTime.now())
        .message(exception.getMessage())
        .format(exception.getFormat())
        .arguments( !StringUtils.isEmpty(exception.getArgument()) ? Arrays.asList(exception.getArgument()) : null )
        .build();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badRequestDTO);
  }

  @ExceptionHandler(value = InvalidGrantException.class)
  ResponseEntity<?> handleInvalidGrantException(InvalidGrantException exception, WebRequest request) {
    LOGGER.warn("Request '{}' responsed status: '{}'", request.getDescription(false), HttpStatus.UNAUTHORIZED);

    Map<String, String> bodyResp = new HashMap<>();
    bodyResp.put(InvalidGrantException.TIME_ERROR, OffsetDateTime.now().toString());
    bodyResp.put(InvalidGrantException.STATUS, String.format("%s", HttpStatus.UNAUTHORIZED.value()));
    bodyResp.put(InvalidGrantException.ERROR, exception.getError());
    bodyResp.put(InvalidGrantException.DESCRIPTION, exception.getErrorDescription() == null ? "" : exception.getErrorDescription());

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(bodyResp);
  }
}
