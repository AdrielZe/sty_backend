package com.example.sty_backend_def.infra.error;

public record ErrorResponseDTO(String message, int status, String timestamp) {}