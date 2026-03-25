package com.fut_sexta.fut_sexta.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer goals;


    @ManyToOne
    @JoinColumn(name = "team_id")
    @Column(nullable = false)
    private Team team;

    public Player(String name){
        this.name = name;
        goals = 0;
        team = null;
    }

}
