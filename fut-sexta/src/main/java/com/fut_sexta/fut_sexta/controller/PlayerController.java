package com.fut_sexta.fut_sexta.controller;


import com.fut_sexta.fut_sexta.DTO.PlayerDTO;
import com.fut_sexta.fut_sexta.mapper.PlayerMapper;
import com.fut_sexta.fut_sexta.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService service;
    private final PlayerMapper mapper;


    @PostMapping
    public ResponseEntity<String> createPlayer(@RequestBody String name){
        service.createPlayer(name);


        return ResponseEntity.status(HttpStatus.CREATED).body(name);
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getPlayers(){
        return ResponseEntity.ok(service.getPlayers().stream()
                .map(mapper::toDTO).toList());
    }


}
