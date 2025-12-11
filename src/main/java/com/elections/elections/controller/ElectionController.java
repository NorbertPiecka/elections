package com.elections.elections.controller;

import com.elections.elections.model.dto.ElectionDTO;
import com.elections.elections.model.entity.Candidate;
import com.elections.elections.model.entity.Election;
import com.elections.elections.service.ElectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elections/v1")
@RequiredArgsConstructor
public class ElectionController {
    private final ElectionService electionService;

    @GetMapping("/election")
    public ResponseEntity<List<Election>> getAllElections() {
        List<Election> elections = electionService.findAllElections();
        return ResponseEntity.ok(elections);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/election")
    public ResponseEntity<Election> createNewElection(@Valid @RequestBody ElectionDTO dto) {
        try {
            Election election = electionService.createNewElection(dto);
            return new ResponseEntity<>(election, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/election/{electionId}/candidate")
    public ResponseEntity<Candidate> addCandidate(@PathVariable Long electionId, @RequestParam String candidateName) {
        Candidate candidate = electionService.addCandidateToElection(electionId, candidateName);
        return new ResponseEntity<>(candidate, HttpStatus.CREATED);
    }
}
