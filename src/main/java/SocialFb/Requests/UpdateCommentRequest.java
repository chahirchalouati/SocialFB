package SocialFb.Requests;

import lombok.Data;

@Data
public class UpdateCommentRequest {
    private long id;
    private String content;
}
