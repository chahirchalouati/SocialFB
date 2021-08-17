package SocialFb.Requests;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostCreateRequest {

    private String content;

    private List<MultipartFile> attachments;

}
