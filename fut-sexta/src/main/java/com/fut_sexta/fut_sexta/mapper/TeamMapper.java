package com.fut_sexta.fut_sexta.mapper;

import com.fut_sexta.fut_sexta.DTO.TeamDTO;
import com.fut_sexta.fut_sexta.model.Player;
import com.fut_sexta.fut_sexta.model.Team;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamMapper {


    public TeamDTO toDTO(Team team){
        List<String> playersName = team.getPlayers().stream().map(Player::getName).toList();

        return new TeamDTO(team.getName(), playersName);
    }
}
