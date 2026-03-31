package com.fut_sexta.fut_sexta.DTO;

import java.time.LocalDate;

public record MatchOutputDTO(String teamA,
                             String teamB,
                             int minutos,
                             int scoreA,
                             int scoreB,
                             LocalDate localDate) {
}
