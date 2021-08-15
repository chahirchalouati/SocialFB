package SocialFb.Requests;

import SocialFb.Models.Attachment;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
public class PostUpdateRequest {

    private String content;

    private Set<MultipartFile> newAttachments;

    private Set<Attachment> oldAttachments;
}
