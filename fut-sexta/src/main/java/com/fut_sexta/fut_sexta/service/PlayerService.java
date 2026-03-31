package com.fut_sexta.fut_sexta.service;


import com.fut_sexta.fut_sexta.model.Player;
import com.fut_sexta.fut_sexta.model.Team;
import com.fut_sexta.fut_sexta.repository.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository repository;



    public Player createPlayer(String name){
        if (repository.existsByName(name)) throw new IllegalArgumentException("Nome já cadastrado");
        return repository.save(new Player(name));
    }

    public List<Player> getPlayers(){
        return repository.findAll();
    }

    public Player getById(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Player não encontrado"));
    }

}
