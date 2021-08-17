package SocialFb.Exceptions;

/***
 * Created by Chahir Chalouati
 * in 8/17/2021
 */
public class InvalidAttachmentTypeException extends RuntimeException {
    public InvalidAttachmentTypeException () {
    }

    public InvalidAttachmentTypeException (String message) {
        super(message);
    }

    public InvalidAttachmentTypeException (String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAttachmentTypeException (Throwable cause) {
        super(cause);
    }

    public InvalidAttachmentTypeException (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
