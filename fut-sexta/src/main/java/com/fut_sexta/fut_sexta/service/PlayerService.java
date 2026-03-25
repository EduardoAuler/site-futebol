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

    public Player createPlayer(String name){
        if (repository.existsByName(name)) throw new IllegalArgumentException("Nome já cadastrado");
        return repository.save(new Player(name));
    }

}
