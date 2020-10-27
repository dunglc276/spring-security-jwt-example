package com.lcd.jwt.api.advice.mvc;

import com.lcd.jwt.api.exception.InvalidGrantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

  private static final long serialVersionUID = 1L;

  @Autowired
  private HandlerExceptionResolver handlerExceptionResolver;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

    InvalidGrantException  invalidGrantException = InvalidGrantException.builder()
        .error(InvalidGrantException.INVALID_GRANT)
        .errorDescription(authException.getMessage())
        .build();

    handlerExceptionResolver.resolveException(request, response, null, invalidGrantException);
  }
}
