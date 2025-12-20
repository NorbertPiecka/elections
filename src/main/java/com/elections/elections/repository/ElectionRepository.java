package com.elections.elections.repository;

import com.elections.elections.model.entity.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ElectionRepository extends JpaRepository<Election, Long> {
    boolean existsByName(String name);
    List<Election> findByStartDateTimeBeforeAndEndDateTimeAfter(LocalDateTime date1, LocalDateTime date2);
    @Modifying
    @Query("DELETE FROM Election e WHERE e.id = :id")
    int deleteElectionById(@Param("id") Long id);
}
