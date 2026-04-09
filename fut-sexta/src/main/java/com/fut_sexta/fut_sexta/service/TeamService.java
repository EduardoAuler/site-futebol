package com.fut_sexta.fut_sexta.service;


import com.fut_sexta.fut_sexta.model.Player;
import com.fut_sexta.fut_sexta.model.Team;
import com.fut_sexta.fut_sexta.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final PlayerService playerService;



    public Team getById(Long id){
        return teamRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Time não encontrado"));
    }


    public Team createTeam(String name){
        if (teamRepository.existsByName(name)) throw new IllegalArgumentException("Nome já cadastrado");

        return teamRepository.save(new Team(name));
    }

    public List<Team> getTeams(){
        return teamRepository.findAll();
    }


    @Transactional
    public Team changeName(Long id, String name){
        if (teamRepository.existsByName(name)) throw new IllegalArgumentException("Nome já cadastrado");

        Team team = getById(id);
        team.setName(name);
        return teamRepository.save(team);
    }

    @Transactional
    public Team addPlayer(Long id, Long playerId){
        Team team = getById(id);

        Player player = playerService.getById(playerId);

        if (team.getPlayers().contains(player)){
            throw new IllegalArgumentException("Player já está no time");
        }

        team.addPlayer(player);

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
