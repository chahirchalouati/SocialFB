package SocialFb.Requests;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
public class PostCreateRequest {

    private String content;

    private Set<MultipartFile> attachments;

}
