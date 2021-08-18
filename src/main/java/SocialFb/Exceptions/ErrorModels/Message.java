package SocialFb.Exceptions.ErrorModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

/***
 * Created by Chahir Chalouati
 * in 8/18/2021
 */
@Data
@AllArgsConstructor
public class Message {
    String message;
    HttpStatus status;
    LocalDate localDate;
}
