package SocialFb.Helpers;

import SocialFb.Models.FileDetails;
import SocialFb.Models.Size;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/***
 * Created by Chahir Chalouati
 * in 8/17/2021
 */
@Component
@Slf4j
@AllArgsConstructor
public class FileDetailsHepler {
    public FileDetails.SizeLabel sizeLabelHandler (Size size) {

        if ( size.width() == 1024 ) {
            return FileDetails.SizeLabel.LARGE_2X;
        } else if ( size.width() == 780 ) {
            return FileDetails.SizeLabel.LARGE;
        } else if ( size.width() == 860 ) {
            return FileDetails.SizeLabel.LANDSCAPE;
        } else if ( size.width() == 560 ) {
            return FileDetails.SizeLabel.PORTRAIT;
        } else if ( size.width() == 250 ) {
            return FileDetails.SizeLabel.SMALL;
        } else if ( size.width() == 320 ) {
            return FileDetails.SizeLabel.TINY;
        }
        return FileDetails.SizeLabel.ORIGINAL;
    }
}
