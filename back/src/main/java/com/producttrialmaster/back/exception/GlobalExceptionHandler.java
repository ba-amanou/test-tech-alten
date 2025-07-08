package com.producttrialmaster.back.exception;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Map<String, Object> createErrorBody(HttpStatus status, String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return body;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        // on récupére la première erreur uniquement pour simplifier
        FieldError firstError = ex.getBindingResult().getFieldErrors().stream().findFirst().orElse(null);
        String errorMessage = firstError != null
            ? String.format("Le champ '%s' %s", firstError.getField(), firstError.getDefaultMessage())
            : "Erreur de validation inconnue";

        Map<String, Object> errorBody = createErrorBody(HttpStatus.BAD_REQUEST, errorMessage);
        return ResponseEntity.badRequest().body(errorBody);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, Object>> handleApiExceptions(ApiException ex){
        Map<String, Object> errorBody = createErrorBody(ex.getStatus(), ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(errorBody);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> handleAccessDenied(AccessDeniedException ex) {
        Map<String, Object> errorBody = createErrorBody(HttpStatus.FORBIDDEN, "Accès refusé");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorBody);
    }    

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleUnexpectedErrors(Exception ex) {
        ex.printStackTrace(); // journalisation
        Map<String, Object> errorBody = createErrorBody(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorBody);
    }
}
