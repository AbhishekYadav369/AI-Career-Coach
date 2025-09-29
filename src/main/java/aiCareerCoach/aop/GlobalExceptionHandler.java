package aiCareerCoach.aop;

import aiCareerCoach.security.principal.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<String> handleDuplicateUsername(UsernameAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
//          @ExceptionHandler(LlmServiceUnavailableException.class)
//        public ResponseEntity<String> handleLlmServiceUnavailable(LlmServiceUnavailableException ex) {
//            return ResponseEntity
//                    .status(HttpStatus.SERVICE_UNAVAILABLE)
//                    .body(ex.getMessage()); // or return a JSON error object
//        }


        // Handle generic errors
        @ExceptionHandler(Exception.class)
        public ResponseEntity<Map<String, Object>> handleException(Exception ex, WebRequest request) {
            Map<String, Object> body = new HashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.put("error", "Internal Server Error");
            body.put("message", ex.getMessage());
            body.put("path", request.getDescription(false).replace("uri=", ""));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }

        // Handle specific custom exception
        @ExceptionHandler(LlmServiceUnavailableException.class)
        public ResponseEntity<Map<String, Object>> handleLlmServiceUnavailable(LlmServiceUnavailableException ex, WebRequest request) {
            Map<String, Object> body = new HashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("status", HttpStatus.SERVICE_UNAVAILABLE.value());
            body.put("error", "AI Service Unavailable");
            body.put("message", ex.getMessage());
            body.put("path", request.getDescription(false).replace("uri=", ""));
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
        }
}


