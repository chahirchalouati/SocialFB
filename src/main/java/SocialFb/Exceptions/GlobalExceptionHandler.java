package SocialFb.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BadRequestException.class})
    private ResponseEntity BadRequestException (BadRequestException exception) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler({CustomEntityNotFoundException.class})
    private ResponseEntity BadRequestException (CustomEntityNotFoundException exception) {
        return new ResponseEntity(new Message(exception.getMessage(), HttpStatus.NOT_FOUND, LocalDate.now()), HttpStatus.NOT_FOUND);
    }

    @Data
    @AllArgsConstructor
    class Message {
        String message;
        HttpStatus status;
        LocalDate localDate;
    }
}
