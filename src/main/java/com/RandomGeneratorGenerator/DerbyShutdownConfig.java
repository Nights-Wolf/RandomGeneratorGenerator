package com.RandomGeneratorGenerator;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DerbyShutdownConfig {

    @PreDestroy
    public void shutdown() {
        final String SHUTDOWN_CODE = "XJ015";
        System.out.println("SHUTTING DOWN");

        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException e) {

            if (!SHUTDOWN_CODE.equals(e.getSQLState())) {
                e.printStackTrace();
            }
        }
    }
}
