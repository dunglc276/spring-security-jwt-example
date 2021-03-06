package com.lcd.jwt.api.common.uuid.hibernate.id;

import org.hibernate.Session;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerationStrategy;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

public class PsqlUUIDGenerationStrategy implements UUIDGenerationStrategy {

  private static final long serialVersionUID = 1614776511384024304L;

  @Override
  public int getGeneratedVersion() {
    return 4;
  }

  @Override
  public UUID generateUUID(SharedSessionContractImplementor session) {
    return ((Session) session).doReturningWork(connection -> {
      try (
          Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery("select uuid_generate_v4()")) {
        while (resultSet.next()) {
          return (UUID) resultSet.getObject(1);
        }
      }
      throw new IllegalArgumentException("Can't fetch a new UUID");
    });
  }

}
