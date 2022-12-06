package com.org.backend_nafinsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

import java.util.logging.Logger;

@EnableCaching
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class BackendNafinsaApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendNafinsaApplication.class, args);
    }

}