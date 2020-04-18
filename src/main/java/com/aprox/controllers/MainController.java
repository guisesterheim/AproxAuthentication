package com.aprox.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping
    @RequestMapping("/")
    public ResponseEntity<String> redirect(){
        return healthCheck();
    }

    @GetMapping
    @RequestMapping("/healthCheck")
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity.ok("HealthCheck OK!");
    }
}
