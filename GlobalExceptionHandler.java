package com.ws101.galit.ecommercefullstack.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.ws101.galit.ecommercefullstack.exception.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for the entire application.
 *
 * <p>{@code @RestControllerAdvice} intercepts exceptions thrown by any
 * {@code @RestController} and allows returning structured JSON error responses
 * instead of Spring's default error page or stack trace.</p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link DataIntegrityViolationException} — returns HTTP 400.
     *
     * <p>Triggered when a database constraint is violated, such as a duplicate
     * unique key or a NOT NULL constraint being broken.</p>
     *
     * @param ex the data integrity violation exception
     * @return JSON error response with status 400
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrity(DataIntegrityViolationException ex) {
        String message = ex.getMostSpecificCause() != null
                ? ex.getMostSpecificCause().getMessage()
                : ex.getMessage();
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Data integrity violation: " + message);
    }

    /**
     * Handles bean validation errors from {@code @Valid} annotations — returns HTTP 422.
     *
     * <p>Collects all field-level validation errors and returns them as a map
     * of {@code field → error message}.</p>
     *
     * @param ex the validation exception containing all field errors
     * @return JSON map of field errors with status 422
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.UNPROCESSABLE_ENTITY.value());
        body.put("error", "Validation Failed");
        body.put("errors", fieldErrors);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(body);
    }

    /**
     * Catch-all handler for any unexpected exceptions — returns HTTP 500.
     *
     * @param ex the unexpected exception
     * @return JSON error response with status 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + ex.getMessage());
    }

    // -------------------------------------------------------------------------
    // Private helper
    // -------------------------------------------------------------------------

    /**
     * Builds a consistent JSON error response body.
     *
     * @param status  HTTP status to return
     * @param message human-readable error description
     * @return structured ResponseEntity with the error payload
     */
    ResponseEntity<Map<String, Object>> buildErrorResponse(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return ResponseEntity.status(status).body(body);
    }

    /**
     * Handles {@link ResourceNotFoundException} — returns HTTP 404.
     *
     * @param resourceNotFoundException the resource not found exception
     * @return JSON error response with status 404
     */
   
}
