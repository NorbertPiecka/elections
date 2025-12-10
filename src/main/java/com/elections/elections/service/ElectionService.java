package com.elections.elections.service;

import com.elections.elections.model.entity.Candidate;
import com.elections.elections.model.entity.Election;
import com.elections.elections.repository.CandidateRepository;
import com.elections.elections.repository.ElectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ElectionService {
    private final ElectionRepository electionRepository;
    private final CandidateRepository candidateRepository;

    public List<Election> findAllElections() {
        return electionRepository.findAll();
    }

    public List<Election> findCurrentElections() {
        LocalDateTime today = LocalDateTime.now();
        return electionRepository.findByStartDateTimeBeforeAndEndDateTimeAfter(today, today);
    }

    @Transactional
    public Election createNewElection(Election election) {
        if (electionRepository.existsByName(election.getName())) {
            throw new IllegalArgumentException("Election with name: " + election.getName() + ", already exists");
        }
        if (election.getStartDateTime().isAfter(election.getEndDateTime())) {
            throw new IllegalArgumentException("Start date of election need to be before end date");
        }
        return electionRepository.save(election);
    }

    @Transactional
    public Candidate addCandidateToElection(Long electionId, String candidateName) {
        Election election = electionRepository.findById(electionId)
                .orElseThrow(() -> new IllegalArgumentException("Election has not been found"));

        Candidate candidate = new Candidate();
        candidate.setName(candidateName);
        candidate.setElection(election);

        return candidateRepository.save(candidate);
    }
}
