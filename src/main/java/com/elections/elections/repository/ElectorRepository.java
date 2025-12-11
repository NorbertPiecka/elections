package com.elections.elections.repository;

import com.elections.elections.model.entity.Elector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ElectorRepository extends JpaRepository<Elector, Long> {
    Optional<Elector> findByLogin(String login);
    boolean existsByLogin(String login);
    long countByIsLockedFalse();
}
