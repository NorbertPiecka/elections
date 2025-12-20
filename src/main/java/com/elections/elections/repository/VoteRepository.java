package com.elections.elections.repository;

import com.elections.elections.model.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByElectorIdAndElectionId(Long electorId, Long electionId);
    long countByCandidateIdAndElectionId(Long candidateId, Long electionId);
    long countByElectionId(Long electionId);
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.candidate.id = :candidateId")
    void deleteByCandidateId(@Param("candidateId") Long candidateId);
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.election.id = :electionId")
    void deleteByElectionId(@Param("electionId") Long electionId);
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.elector.id = :electorId")
    void deleteByElectorId(@Param("electorId") Long electorId);
}
