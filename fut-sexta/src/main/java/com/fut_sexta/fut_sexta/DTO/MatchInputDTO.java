package com.fut_sexta.fut_sexta.DTO;

import java.time.LocalDate;

public record MatchInputDTO(Long teamAId,
                            Long teamBId,
                            int minutos) {
}
