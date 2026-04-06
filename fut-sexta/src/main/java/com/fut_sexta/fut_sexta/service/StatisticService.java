package com.fut_sexta.fut_sexta.service;


import com.fut_sexta.fut_sexta.DTO.ArtilheiroDTO;
import com.fut_sexta.fut_sexta.model.Match;
import com.fut_sexta.fut_sexta.repository.GoalRepository;
import com.fut_sexta.fut_sexta.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final GoalRepository goalRepository;
    private final MatchRepository matchRepository;

    public List<ArtilheiroDTO> findArtilheiros(){
        return goalRepository.findArtilheiros();
    }

    public List<Match> listarHistorico(){
        return matchRepository.findByFinishedTrue();
    }

}
