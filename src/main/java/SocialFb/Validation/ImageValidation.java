package SocialFb.Validation;

import org.springframework.stereotype.Component;

/***
 * Created by Chahir Chalouati
 * in 8/17/2021
 */
@Component
public class ImageValidation {
    public static void validateDimensions (int var0, int var1) {
        if ( var0 <= 0 && var1 <= 0 )
            throw new IllegalArgumentException("Destination image dimensions must not be less than 0 pixels.");
        else if ( var0 <= 0 || var1 <= 0 ) {
            String var2 = var0 == 0 ? "width" : "height";
            throw new IllegalArgumentException("Destination image " + var2 + " must not be " + "less than or equal to 0 pixels.");
        }
    }

    public static void checkForNull (Object var0, String var1) {
        if ( var0 == null ) {
            throw new NullPointerException(var1);
        }
    }

    public static void checkForEmpty (Object[] var0, String var1) {
        if ( var0.length == 0 ) {
            throw new IllegalArgumentException(var1);
        }
    }

    public static void checkForEmpty (Iterable<?> var0, String var1) {
        if ( !var0.iterator().hasNext() ) {
            throw new IllegalArgumentException(var1);
        }
    }
}
