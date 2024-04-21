package com.example.library.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ErrorResponse {
    private final boolean success = false;
    private final LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String message;
    private List<String> errors;
}
