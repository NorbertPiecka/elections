package com.elections.elections.controller;

import com.elections.elections.model.dto.ElectorRegistrationDTO;
import com.elections.elections.model.entity.Elector;
import com.elections.elections.service.ElectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elections/v1/elector")
@RequiredArgsConstructor
public class ElectorController {
    private final ElectorService electorService;

    @PostMapping
    public ResponseEntity<Elector> registerElector(@Valid @RequestBody ElectorRegistrationDTO dto) {
        Elector elector = electorService.createNewElector(dto.getLogin(), dto.getPassword(), dto.getName(), dto.getSurname(), dto.getRole());
        return new ResponseEntity<>(elector, HttpStatus.CREATED);
    }
}
