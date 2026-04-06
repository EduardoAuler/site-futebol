package com.fut_sexta.fut_sexta.repository;

import com.fut_sexta.fut_sexta.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByFinishedTrue();
}
