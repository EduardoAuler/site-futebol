package com.fut_sexta.fut_sexta.mapper;

import com.fut_sexta.fut_sexta.DTO.PlayerDTO;
import com.fut_sexta.fut_sexta.model.Player;

public class PlayerMapper {

    public PlayerDTO toDTO(Player p){
        return new PlayerDTO(p.getId(), p.getName());
    }
}
