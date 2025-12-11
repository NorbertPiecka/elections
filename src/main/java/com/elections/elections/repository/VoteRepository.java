package com.elections.elections.repository;

import com.elections.elections.model.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByElectorIdAndElectionId(Long electorId, Long electionId);
    long countByCandidateIdAndElectionId(Long candidateId, Long electionId);
    long countByElectionId(Long electionId);
    @Modifying
    void deleteByCandidateId(Long candidateId);
    @Modifying
    void deleteByElectionId(Long electionId);
    @Modifying
    void deleteByElectorId(Long electorId);
}
