package SocialFb.Exceptions;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/***
 * Created by Chahir Chalouati
 * in 8/18/2021
 */

public abstract class BaseErrorTemplate extends RuntimeException {

    private List<Error> errors;

    public BaseErrorTemplate (String message) {
        super(message);
    }

    public BaseErrorTemplate (List<Error> errors) {
        this.errors = errors;
    }

    public BaseErrorTemplate (String message, List<Error> errors) {
        super(message);
        this.errors = errors;
    }

    public BaseErrorTemplate (String message, Throwable cause, List<Error> errors) {
        super(message, cause);
        this.errors = errors;
    }

    public BaseErrorTemplate (Throwable cause, List<Error> errors) {
        super(cause);
        this.errors = errors;
    }

    public BaseErrorTemplate (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<Error> errors) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errors = errors;
    }

    public List<Error> getErrors () {
        return errors;
    }

    public void setErrors (List<Error> errors) {
        this.errors = errors;
    }


    @Data
    @Builder
   public static class Error {
        String message;
        String error;
        String filed;
        String cause;
        String toFix;
    }
}
