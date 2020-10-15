package com.lcd.jwt.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;

import javax.persistence.*;
import org.hibernate.annotations.Parameter;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lcd_user" )
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "pg-uuid")
  @GenericGenerator(name = "pg-uuid",
      strategy = "uuid2",
      parameters = @Parameter(name = UUIDGenerator.UUID_GEN_STRATEGY_CLASS,
          value = "com.lcd.jwt.api.common.uuid.hibernate.id.PsqlUUIDGenerationStrategy"))
  private UUID id;

  private String username;

  private String password;

  private String email;

}
