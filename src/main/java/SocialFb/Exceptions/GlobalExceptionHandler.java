package SocialFb.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BadRequestException.class})
    private ResponseEntity BadRequestException (BadRequestException exception) {
        return ResponseEntity.badRequest().build();
    }
}
