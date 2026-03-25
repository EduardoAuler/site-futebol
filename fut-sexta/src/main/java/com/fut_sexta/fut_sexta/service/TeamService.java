package com.fut_sexta.fut_sexta.service;


import com.fut_sexta.fut_sexta.model.Player;
import com.fut_sexta.fut_sexta.model.Team;
import com.fut_sexta.fut_sexta.repository.PlayerRepository;
import com.fut_sexta.fut_sexta.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;


    public TeamService(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    public Team getById(Long id){
        return teamRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Time não encontrado"));
    }


    public Team createTeam(Team team){
        if (teamRepository.existsByName(team.getName())) throw new IllegalArgumentException("Nome já cadastrado");

        return teamRepository.save(team);
    }


    @Transactional
    public Team changeName(Long id, String name){
        if (teamRepository.existsByName(name)) throw new IllegalArgumentException("Nome já cadastrado");

        Team team = getById(id);
        team.setName(name);
        return teamRepository.save(team);
    }

    @Transactional
    public Team addPlayer(Long id, List<Long> playersId){
        Team team = getById(id);

        List<Player> players = playerRepository.findAllById(playersId);

        players.stream()
                .filter(p -> team.getPlayers().stream()
                        .noneMatch(tp -> tp.getId().equals(p.getId())))
                .forEach(team::addPlayer);

        return teamRepository.save(team);
    }

    @Transactional
    public Team removePlayer(Long teamId, Long playerId){

        Team team = getById(teamId);

        boolean removed = team.getPlayers()
                .removeIf(p -> p.getId().equals(playerId));

        if (!removed) {
            throw new IllegalArgumentException("Player não está no time");
        }

        return teamRepository.save(team);
    }


    @Transactional
    public void deleteTeam(Long id){
        Team team = getById(id);

        teamRepository.delete(team);
    }

}
