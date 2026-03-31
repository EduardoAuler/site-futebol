package com.fut_sexta.fut_sexta.DTO;

import com.fut_sexta.fut_sexta.model.TeamSide;

public record GoalDTO(Long matchId,
                      Long playerId,
                      TeamSide side) {
}
