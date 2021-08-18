package SocialFb.Exceptions;

import java.util.List;

public class BadRequestException extends BaseErrorTemplate {

    public BadRequestException (String message) {
        super(message);
    }

    public BadRequestException (List<BaseErrorTemplate.Error> errors) {
        super(errors);
    }

    public BadRequestException (String message, List<BaseErrorTemplate.Error> errors) {
        super(message, errors);
    }

    public BadRequestException (String message, Throwable cause, List<BaseErrorTemplate.Error> errors) {
        super(message, cause, errors);
    }

    public BadRequestException (Throwable cause, List<BaseErrorTemplate.Error> errors) {
        super(cause, errors);
    }

    public BadRequestException (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<BaseErrorTemplate.Error> errors) {
        super(message, cause, enableSuppression, writableStackTrace, errors);
    }
}
