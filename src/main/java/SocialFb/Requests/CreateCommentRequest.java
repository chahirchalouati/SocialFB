package SocialFb.Requests;

import SocialFb.Models.Post;
import SocialFb.Models.Reaction;
import SocialFb.Models.Reply;
import lombok.Data;

import java.util.Set;

@Data
public class CreateCommentRequest {
    private Post post;
    private String content;
    private Set<Reply> replies;
    private Set<Reaction> reactions;
}
