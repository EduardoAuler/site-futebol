package com.fut_sexta.fut_sexta.repository;

import com.fut_sexta.fut_sexta.DTO.ArtilheiroDTO;
import com.fut_sexta.fut_sexta.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    @Query("""
    SELECT new com.fut_sexta.fut_sexta.DTO.ArtilheiroDTO(p.name, COUNT(g))
    FROM Goal g
    JOIN g.player p
    GROUP BY p.name
    ORDER BY COUNT(g) DESC
""")
    List<ArtilheiroDTO> findArtilheiros();
}
