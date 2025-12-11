package com.elections.elections.controller;

import com.elections.elections.model.dto.VoteDTO;
import com.elections.elections.model.entity.Elector;
import com.elections.elections.model.entity.Vote;
import com.elections.elections.service.VoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/elections/v1/vote")
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<Vote> castVote(@Valid @RequestBody VoteDTO dto, @AuthenticationPrincipal Elector elector) {
        Vote vote = voteService.castVote(elector.getId(), dto.getElectionId(), dto.getCandidateId());
        return new ResponseEntity<>(vote, HttpStatus.CREATED);
    }

    @GetMapping("/results/{electionId}/candidate/{candidateId}/votes")
    public ResponseEntity<Map<String, Long>> getCandidateVotes(@PathVariable Long electionId, @PathVariable Long candidateId) {
        Map<String, Long> votes = voteService.getCandidateVotes(candidateId, electionId);
        return ResponseEntity.ok(votes);
    }

    @GetMapping("/results/{electionId}/candidate/{candidateId}")
    public ResponseEntity<Map<String, Double>> getCandidateResults(@PathVariable Long electionId, @PathVariable Long candidateId) {
        Map<String, Double> candidatePrecent = voteService.getCandidateVotesPrecent(candidateId, electionId);
        return ResponseEntity.ok(candidatePrecent);
    }

    @GetMapping("/results/{electionId}")
    public ResponseEntity<Map<String, Double>> getResults(@PathVariable Long electionId) {
        Map<String, Double> results = voteService.getElectionResults(electionId);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/results/{electionId}/attendance")
    public ResponseEntity<Map<String, Double>> getElectionAttendance(@PathVariable Long electionId) {
        Map<String, Double> attendance = voteService.calculateAttendance(electionId);
        return ResponseEntity.ok(attendance);
    }

}
