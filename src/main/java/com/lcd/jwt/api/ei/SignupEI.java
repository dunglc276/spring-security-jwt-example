package com.lcd.jwt.api.ei;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonDeserialize(builder = SignupEI.SignupEIBuilder.class)
public class SignupEI {

  @NotNull
  @NotBlank
  @Length(min = 1, max=50)
  private String username;

  @NotNull
  @NotBlank
  @Length(min = 1, max=50)
  private String email;

  @NotNull
  @NotBlank
  @Length(min = 1, max=50)
  private String password;

  @JsonPOJOBuilder(withPrefix = "")
  public static class SignupEIBuilder {
  }
}
