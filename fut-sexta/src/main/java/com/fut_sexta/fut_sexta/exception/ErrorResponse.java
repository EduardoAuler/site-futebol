package com.fut_sexta.fut_sexta.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponse(String message,
                            HttpStatus status,
                            LocalDateTime localDateTime) {
}
