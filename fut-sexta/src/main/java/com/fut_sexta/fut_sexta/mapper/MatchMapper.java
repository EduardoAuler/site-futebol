package com.fut_sexta.fut_sexta.mapper;

import com.fut_sexta.fut_sexta.DTO.MatchInputDTO;
import com.fut_sexta.fut_sexta.DTO.MatchOutputDTO;
import com.fut_sexta.fut_sexta.model.Match;
import com.fut_sexta.fut_sexta.service.MatchService;
import com.fut_sexta.fut_sexta.service.TeamService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MatchMapper {

    private final TeamService service;

    public Match toEntity(MatchInputDTO input){
        String teamA = service.getById(input.teamAId()).getName();
        String teamB = service.getById(input.teamBId()).getName();
        return new Match(teamA, teamB, input.minutos());
    }

    public MatchOutputDTO toDTO(Match match){
        return new MatchOutputDTO(match.getTeamAName(), match.getTeamBName(),
                match.getMinutos(), match.getScoreA(), match.getScoreB(),
                match.getLocalDate());
    }

}
