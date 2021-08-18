package SocialFb.Exceptions.ErrorModels;

import SocialFb.Exceptions.BaseErrorTemplate;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

/***
 * Created by Chahir Chalouati
 * in 8/18/2021
 */
@Data
@Builder
public class ErrorMessage {
    List<BaseErrorTemplate.Error> errors;
    String message;
    Instant instant;
}