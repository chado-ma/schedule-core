package com.schedulecore.ufu.infrasctructure.errorHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        log.error("Illegal argument exception in class {} in {}: {}", ex.getStackTrace()[0].getClassName(), ex.getStackTrace()[0].getMethodName(), ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleIllegalArgument(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException in class {} in {}: {}", ex.getStackTrace()[0].getClassName(), ex.getStackTrace()[0].getMethodName(), ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getLocalizedMessage());
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<String> handleIllegalArgument(AuthenticationCredentialsNotFoundException ex) {
        log.error("AuthenticationCredentialsNotFoundException argument exception in class {} in {}: {}", ex.getStackTrace()[0].getClassName(), ex.getStackTrace()[0].getMethodName(), ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        log.error("Unexpected error occurred in class {} in {}: {}", ex.getStackTrace()[0].getClassName(), ex.getStackTrace()[0].getMethodName(), ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body("Ocorreu um erro inesperado: " + ex.getMessage());
    }
}
