package com.fut_sexta.fut_sexta.repository;

import com.fut_sexta.fut_sexta.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

    boolean existsByName(String name);
}
