package com.fut_sexta.fut_sexta.service;


import com.fut_sexta.fut_sexta.model.Goal;
import com.fut_sexta.fut_sexta.model.Match;
import com.fut_sexta.fut_sexta.model.Player;
import com.fut_sexta.fut_sexta.model.TeamSide;
import com.fut_sexta.fut_sexta.repository.GoalRepository;
import com.fut_sexta.fut_sexta.repository.MatchRepository;
import com.fut_sexta.fut_sexta.repository.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;
    private final GoalRepository goalRepository;


    public Match getById(Long id){
        return matchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Partida não encontrada"));
    }

    public MatchService(MatchRepository matchRepository, PlayerRepository playerRepository, GoalRepository goalRepository) {
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
        this.goalRepository = goalRepository;
    }

    public Match createMatch(String nameA, String nameB, int minutos){
        Match match = new Match(nameA,nameB,minutos);
        return matchRepository.save(match);
    }


    @Transactional
    public Match endMatch(Long id){
        Match match = getById(id);

        if (match.isFinished()) throw new IllegalArgumentException("Partida já encerrada");

        match.setFinished(true);

        return matchRepository.save(match);
    }

    @Transactional
    public Match addGoal(Long matchId, Long playerId, TeamSide side){
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new EntityNotFoundException("Partida não encontrada"));

        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new EntityNotFoundException("Jogador não encontrado"));

        Goal goal = new Goal(player, match, side);

        if (side == TeamSide.A){
            match.setScoreA(match.getScoreA() + 1);
        }
        if (side == TeamSide.B){
            match.setScoreB(match.getScoreA() + 1);
        }

        goalRepository.save(goal);

        return match;
    }


    @Transactional
    public void removeGoal(Long goalId){
        Goal goal = goalRepository.findById(goalId).orElseThrow(() -> new EntityNotFoundException("Gol não encontrado"));

        Match match = goal.getMatch();
        match.removeGoal(goal);

        if (goal.getTeamSide() == TeamSide.A){
            match.setScoreA(match.getScoreA() - 1);
        }

        if (goal.getTeamSide() == TeamSide.B){
            match.setScoreB(match.getScoreB() - 1);
        }

        goalRepository.delete(goal);
    }
}
