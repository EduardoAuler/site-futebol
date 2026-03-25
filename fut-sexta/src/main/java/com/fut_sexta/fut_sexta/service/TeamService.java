package com.fut_sexta.fut_sexta.service;


import com.fut_sexta.fut_sexta.repository.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    private final TeamRepository repository;


    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }


}
