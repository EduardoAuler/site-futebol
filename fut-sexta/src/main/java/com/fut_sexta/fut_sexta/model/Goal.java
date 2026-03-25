package com.fut_sexta.fut_sexta.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "gols")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Player player;

    @ManyToOne
    private Match match;

    @Enumerated(EnumType.STRING)
    private TeamSide teamSide;


    public Goal(Player player, Match match, TeamSide teamSide){
        this.player = player;
        this.match = match;
        this.teamSide = teamSide;
    }
}
