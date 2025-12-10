package com.elections.elections.repository;

import com.elections.elections.model.entity.Election;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ElectionRepository extends JpaRepository<Election, Long> {
    boolean existsByName(String name);
    List<Election> findByStartDateTimeBeforeAndEndDateTimeAfter(LocalDateTime date1, LocalDateTime date2);
}
