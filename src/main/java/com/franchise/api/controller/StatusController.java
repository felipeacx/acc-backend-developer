package com.franchise.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private DataSource dataSource;

    @GetMapping
    public ResponseEntity<String> status() {
        try (Connection conn = dataSource.getConnection()) {
            return ResponseEntity.ok("Database connected successfully");
        } catch (SQLException e) {
            return ResponseEntity.status(500).body("Database connection failed: " + e.getMessage());
        }
    }
}
