package com.fut_sexta.fut_sexta.controller;


import com.fut_sexta.fut_sexta.DTO.ArtilheiroDTO;
import com.fut_sexta.fut_sexta.DTO.MatchOutputDTO;
import com.fut_sexta.fut_sexta.mapper.MatchMapper;
import com.fut_sexta.fut_sexta.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistic")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService service;
    private final MatchMapper mapper;


    @GetMapping("/artilheiro")
    public ResponseEntity<List<ArtilheiroDTO>> getArtilheiros(){
        return ResponseEntity.ok(service.findArtilheiros());
    }

    @GetMapping("/historico")
    public ResponseEntity<List<MatchOutputDTO>> getHistorico(){
        List<MatchOutputDTO> dto = service.listarHistorico().stream()
                .map(mapper::toDTO).toList();

        return ResponseEntity.ok(dto);
    }


}
