package com.telros.profiles.exceptions;

import org.springframework.http.HttpStatus;

public record ErrorResponse(HttpStatus status, String message) {
}
