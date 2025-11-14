package com.franchise.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Clase principal de la aplicación Spring Boot para la API de Franquicias
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.franchise.api")
@EnableJpaRepositories(basePackages = "com.franchise.api.repository")
public class FranchiseApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FranchiseApiApplication.class, args);
    }
}

