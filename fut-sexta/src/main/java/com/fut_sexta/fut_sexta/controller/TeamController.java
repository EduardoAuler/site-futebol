package com.fut_sexta.fut_sexta.controller;


import com.fut_sexta.fut_sexta.DTO.TeamDTO;
import com.fut_sexta.fut_sexta.mapper.TeamMapper;
import com.fut_sexta.fut_sexta.model.Player;
import com.fut_sexta.fut_sexta.model.Team;
import com.fut_sexta.fut_sexta.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService service;
    private final TeamMapper mapper;

    @PostMapping
    public ResponseEntity<String> createTeam(@RequestBody String name){
        service.createTeam(name);

        return ResponseEntity.status(HttpStatus.CREATED).body(name);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<TeamDTO> changeName(@PathVariable Long id, @RequestBody String name){
        Team team = service.changeName(id, name);
        TeamDTO dto = mapper.toDTO(team);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PutMapping("/{id}/add/{playerId}")
    public ResponseEntity<TeamDTO> addPlayer(@PathVariable Long id, @PathVariable Long playerId){
        TeamDTO dto = mapper.toDTO(service.addPlayer(id, playerId));

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PutMapping("/{id}/remove/{playerId}")
    public ResponseEntity<TeamDTO> removePlayer(@PathVariable Long id, @PathVariable Long playerId){
        TeamDTO dto = mapper.toDTO(service.removePlayer(id, playerId));

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id){
        service.deleteTeam(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
