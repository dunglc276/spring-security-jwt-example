package com.lcd.jwt.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.util.TimeZone;

@SpringBootApplication
public class SpringSecurityJwtExampleApplication {

  private static final Logger log = LoggerFactory.getLogger(SpringSecurityJwtExampleApplication.class);

  public static void main(String[] args) throws Exception {
    SpringApplication application = new SpringApplication(SpringSecurityJwtExampleApplication.class);
    Environment env = application.run(args).getEnvironment();
    log
        .info("\n----------------------------------------------------------\n\t" + "Application '{}' is running! Access URLs:\n\t"
            + "External: \thttp://{}:{}\n----------------------------------------------------------\n" + "With Timezone: " + TimeZone.getDefault().getDisplayName() + " ("
            + TimeZone.getDefault().getID() + ")", env
            .getProperty("spring.application.name"), InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"));  }

}
