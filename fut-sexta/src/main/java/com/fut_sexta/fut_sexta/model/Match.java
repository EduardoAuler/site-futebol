package com.fut_sexta.fut_sexta.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "partidas")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamAName;
    private String teamBName;

    private int scoreA;
    private int scoreB;

    private int minutos;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    private List<Goal> goals;

    private boolean finished;

    private LocalDate localDate;


    public Match(String teamAName, String teamBName, int minutos){
        this.teamAName = teamAName;
        this.teamBName = teamBName;

        scoreA = 0;
        scoreB = 0;

        this.minutos = minutos;
        goals = new ArrayList<>();

        finished = false;
        localDate = LocalDate.now();
    }

    public void removeGoal(Goal goal) {
        this.goals.remove(goal);
        goal.setMatch(null);
    }
}
