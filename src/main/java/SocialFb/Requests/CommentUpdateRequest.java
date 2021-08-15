package SocialFb.Requests;

import SocialFb.DTOs.ReactionDTO;
import SocialFb.DTOs.ReplyDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class CommentUpdateRequest {

    @NotBlank(message = "post can't be blank")
    private long post_id;

    @NotBlank(message = "content can't be blank")
    private String content;

    private Set<ReplyDTO> replies;

    private Set<ReactionDTO> reactions;

}
