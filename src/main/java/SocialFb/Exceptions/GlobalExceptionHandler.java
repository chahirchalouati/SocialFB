package SocialFb.Exceptions;

import SocialFb.Exceptions.ErrorModels.ErrorMessage;
import SocialFb.Exceptions.ErrorModels.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BadRequestException.class})
    private ResponseEntity<ErrorMessage> badRequestException (BadRequestException exception) {
        final ErrorMessage localMessage = ErrorMessage.builder().errors(exception.getErrors()).message(exception.getMessage()).build();
        return new ResponseEntity<>(localMessage, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({CustomEntityNotFoundException.class, ResourceNotFoundException.class})
    private ResponseEntity<Message> notFoundException (CustomEntityNotFoundException exception) {
        return new ResponseEntity<>(new Message(exception.getMessage(), HttpStatus.NOT_FOUND, LocalDate.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({FileOperationException.class})
    private ResponseEntity<Message> fileOperationException (FileOperationException exception) {
        return new ResponseEntity<>(new Message(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDate.now()), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
