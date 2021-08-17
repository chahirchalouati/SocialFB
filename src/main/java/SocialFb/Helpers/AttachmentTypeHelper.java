package SocialFb.Helpers;

import SocialFb.Enums.AttachmentType;
import org.springframework.web.multipart.MultipartFile;

/***
 * Created by Chahir Chalouati
 * in 8/17/2021
 */

public class AttachmentTypeHelper {
    public static AttachmentType resolve (MultipartFile multipartFile) {
        final String contentType = multipartFile.getContentType();
        assert contentType != null;
        if ( contentType.startsWith("image") ) {
            return AttachmentType.IMAGE;
        } else if ( contentType.startsWith("video") ) {
            return AttachmentType.VIDEO;
        } else if ( contentType.endsWith("pdf") ) {
            return AttachmentType.PDF;
        } else {
            return AttachmentType.FILE;
        }

    }

    public static AttachmentType resolve (String contentType) {
        if ( contentType.startsWith("image") ) {
            return AttachmentType.IMAGE;
        } else if ( contentType.startsWith("video") ) {
            return AttachmentType.VIDEO;
        } else if ( contentType.endsWith("pdf") ) {
            return AttachmentType.PDF;
        } else {
            return AttachmentType.FILE;
        }

    }
}
