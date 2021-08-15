package SocialFb.Utils;

import SocialFb.Enums.AttachmentType;

public class AttachmentTypeResolver {
    public static AttachmentType resolve (String type){
        switch (type){
            case "image":return AttachmentType.IMAGE;
            default:
                return AttachmentType.UNDEFINED;

        }
    }
}
