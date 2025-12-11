package com.elections.elections.service;


import com.elections.elections.model.entity.Candidate;
import com.elections.elections.model.entity.Election;
import com.elections.elections.model.entity.Elector;
import com.elections.elections.model.entity.Vote;
import com.elections.elections.repository.CandidateRepository;
import com.elections.elections.repository.ElectionRepository;
import com.elections.elections.repository.ElectorRepository;
import com.elections.elections.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VoteService {
    private final VoteRepository voteRepository;
    private final ElectorRepository electorRepository;
    private final ElectionRepository electionRepository;
    private final CandidateRepository candidateRepository;

    public Vote castVote(Long electorId, Long electionId, Long candidateId) {
        Elector elector = electorRepository.findById(electorId)
                .orElseThrow(() -> new IllegalArgumentException("Elector has not been found"));
        Election election = electionRepository.findById(electionId)
                .orElseThrow(() -> new IllegalArgumentException("Elections has not been found"));
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new IllegalArgumentException("Candidate has not been found"));

        if (!candidate.getElection().getId().equals(electionId)) {
            throw new IllegalStateException("Candidate: " + candidate.getName() + ", is not taking part in these elections");
        }

        LocalDateTime today = LocalDateTime.now();
        if (today.isBefore(election.getStartDateTime())) {
            throw new IllegalStateException("The election with name: " + election.getName() + ", has not started yet");
        }
        if (today.isAfter(election.getEndDateTime())) {
            throw new IllegalStateException("The election with name: " + election.getName() + ", has ended");
        }

        if (voteRepository.existsByElectorIdAndElectionId(electorId, electionId)) {
            throw new IllegalStateException("The elector has already voted");
        }

        Vote vote = new Vote();
        vote.setElector(elector);
        vote.setElection(election);
        vote.setCandidate(candidate);

        return voteRepository.save(vote);
    }

    public long getCandidateVotes(Long candidateId, Long electionId) {
        return voteRepository.countByCandidateIdAndElectionId(candidateId, electionId);
    }

    public double getCandidateVotesPrecent(Long candidateId, Long electionId) {
        long candidateVotes = getCandidateVotes(candidateId, electionId);
        long allVotes = voteRepository.countByElectionId(electionId);
        if (allVotes == 0) return 0.0;
        return ((double) candidateVotes / allVotes) * 100.0;
    }

    public double calculateAttendance(Long electionId) {
        long allVotes = voteRepository.countByElectionId(electionId);
        long notLockedElectors = electorRepository.countByIsLockedFalse();
        if (notLockedElectors == 0) return 0.0;
        return ((double) allVotes / notLockedElectors) * 100.0;
    }

    public Map<String, Double> getElectionResults(Long electionId) {
        if (!electionRepository.existsById(electionId)) {
            throw new IllegalArgumentException("Election with id: " + electionId + ", doesn't exist");
        }

        List<Candidate> candidates = candidateRepository.findByElectionId(electionId);
        Map<String, Double> results = new LinkedHashMap<>();
        long allVotes = voteRepository.countByElectionId(electionId);

        if (allVotes == 0) {
            candidates.forEach(candidate -> results.put(candidate.getName(), 0.0));
            return results;
        }

        for (Candidate candidate: candidates) {
            double votePrecent = getCandidateVotesPrecent(candidate.getId(), electionId);
            results.put(candidate.getName(), votePrecent);
        }

        return results;
    }
}
