package com.elections.elections.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elections/v1")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<Void> checkApiHealth() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
