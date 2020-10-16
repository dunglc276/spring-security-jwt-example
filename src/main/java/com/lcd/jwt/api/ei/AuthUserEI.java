package com.lcd.jwt.api.ei;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonDeserialize(builder = AuthUserEI.AuthUserEIBuilder.class)
public class AuthUserEI {

  private String username;
  private String password;

  @JsonPOJOBuilder(withPrefix = "")
  public static class AuthUserEIBuilder {
  }
}
