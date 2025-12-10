package com.elections.elections.repository;

import com.elections.elections.model.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByElectorIdAndElectionId(Long electorId, Long electionId);
    long countByCandidateIdAndElectionId(Long candidateId, Long electionId);
    long countByElectionId(Long electionId);
}
