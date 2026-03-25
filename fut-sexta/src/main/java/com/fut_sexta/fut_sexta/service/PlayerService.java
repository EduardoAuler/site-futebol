package com.fut_sexta.fut_sexta.service;


import com.fut_sexta.fut_sexta.model.Player;
import com.fut_sexta.fut_sexta.model.Team;
import com.fut_sexta.fut_sexta.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository repository;


    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public Player findById(Long id){
        Optional<Player> p = repository.findById(id);
        if (p.isEmpty()) throw new NoSuchElementException("Player não encontrado");

        return p.get();
    }

    public Player createPlayer(Player p){
        if (repository.existsByName(p.getName())) throw new IllegalArgumentException("Nome já existe");

        return repository.save(p);
    }

    @Transactional
    public Player increaseGoal(Long id){
        Player player = findById(id);
        player.setGoals(player.getGoals() + 1);

        return player;
    }

    @Transactional
    public Player decreaseGoal(Long id){
        Player player = findById(id);

        if (player.getGoals() == 0) throw new IllegalStateException("Player não possui gols");
        player.setGoals(player.getGoals() - 1);

        return player;
    }

    @Transactional
    public Player addTeam(Long id, Team team){
        Player player = findById(id);
        player.setTeam(team);
        return player;
    }

    @Transactional
    public Player removeTeam(Long id){
        Player player = findById(id);
        player.setTeam(null);
        return player;
    }

}
