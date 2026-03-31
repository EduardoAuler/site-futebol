package com.fut_sexta.fut_sexta.controller;


import com.fut_sexta.fut_sexta.DTO.GoalDTO;
import com.fut_sexta.fut_sexta.DTO.MatchInputDTO;
import com.fut_sexta.fut_sexta.DTO.MatchOutputDTO;
import com.fut_sexta.fut_sexta.mapper.MatchMapper;
import com.fut_sexta.fut_sexta.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService service;
    private final MatchMapper mapper;

    @PostMapping
    public ResponseEntity<MatchOutputDTO> createMatch(@RequestBody MatchInputDTO inputDTO){
        var match = service.createMatch(inputDTO.teamAId(), inputDTO.teamBId(), inputDTO.minutos());

        var dto = mapper.toDTO(match);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PatchMapping("/finish/{id}")
    public ResponseEntity<MatchOutputDTO> finishMatch(@PathVariable Long id){
        MatchOutputDTO dto = mapper.toDTO(service.endMatch(id));

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/add-goal")
    public ResponseEntity<MatchOutputDTO> addGoal(@RequestBody GoalDTO goalDto){
        MatchOutputDTO dto = mapper.toDTO(service.addGoal(goalDto.matchId(),
                goalDto.playerId(),goalDto.side()));

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/remove-goal/{id}")
    public ResponseEntity<MatchOutputDTO> removeGoal(@PathVariable Long id){
        MatchOutputDTO dto = mapper.toDTO(service.removeGoal(id));

        return ResponseEntity.ok(dto);
    }

}
