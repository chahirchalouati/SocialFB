package SocialFb.Exceptions;


public class CustomEntityNotFoundException extends RuntimeException {
    public CustomEntityNotFoundException () {
    }

    public CustomEntityNotFoundException (String message) {
        super(message);
    }

    public CustomEntityNotFoundException (String message, Throwable cause) {
        super(message, cause);
    }

    public CustomEntityNotFoundException (Throwable cause) {
        super(cause);
    }

    public CustomEntityNotFoundException (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
