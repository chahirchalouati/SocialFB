package SocialFb.DTOs;

import SocialFb.Models.Post;
import SocialFb.Models.Reaction;
import SocialFb.Models.Reply;
import SocialFb.Models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommentDTO {

    private Long id;
    private String content;
    private User user;
    private Post post;
    private Set<Reply> replies;
    private Set<Reaction> reactions;
    private Date createdAt;
}
