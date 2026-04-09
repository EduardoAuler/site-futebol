package com.fut_sexta.fut_sexta.service;


import com.fut_sexta.fut_sexta.model.Goal;
import com.fut_sexta.fut_sexta.model.Match;
import com.fut_sexta.fut_sexta.model.Player;
import com.fut_sexta.fut_sexta.model.TeamSide;
import com.fut_sexta.fut_sexta.repository.GoalRepository;
import com.fut_sexta.fut_sexta.repository.MatchRepository;
import com.fut_sexta.fut_sexta.repository.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final PlayerService playerService;
    private final GoalRepository goalRepository;
    private final TeamService teamService;


    public Match getById(Long id){
        return matchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Partida não encontrada"));
    }



    public Match createMatch(Long teamAId, Long teamBId, int minutos){
        String teamA = teamService.getById(teamAId).getName();
        String teamB = teamService.getById(teamBId).getName();

        Match match = new Match(teamA,teamB,minutos);
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

        if (match.isFinished()) throw new IllegalArgumentException("Partida já encerrada");

        Player player = playerService.getById(playerId);

        Goal goal = new Goal(player, match, side);

        if (side == TeamSide.A){
            match.setScoreA(match.getScoreA() + 1);
        }
        if (side == TeamSide.B){
            match.setScoreB(match.getScoreB() + 1);
        }

        goalRepository.save(goal);

        return match;
    }


    @Transactional
    public Match removeGoal(Long goalId){
        Goal goal = goalRepository.findById(goalId).orElseThrow(() -> new EntityNotFoundException("Gol não encontrado"));

        Match match = goal.getMatch();

        if (match.isFinished()) throw new IllegalArgumentException("Partida já encerrada");

        match.removeGoal(goal);

        if (goal.getTeamSide() == TeamSide.A){
            match.setScoreA(match.getScoreA() - 1);
        }

        if (goal.getTeamSide() == TeamSide.B){
            match.setScoreB(match.getScoreB() - 1);
        }

        goalRepository.delete(goal);

        return match;
    }
}
