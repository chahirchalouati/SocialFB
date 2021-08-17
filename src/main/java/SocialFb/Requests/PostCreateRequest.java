package SocialFb.Requests;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Data
public class PostCreateRequest {

    private String content;

    private MultipartFile [] attachments;

    public List <MultipartFile> getAttachmentsAsList () {
        return Arrays.asList(this.attachments);
    }
}
