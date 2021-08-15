package SocialFb.Services;

import SocialFb.DTOs.CommentDTO;
import SocialFb.DTOs.PageDTO;
import SocialFb.DTOs.ReactionDTO;
import SocialFb.DTOs.ReplyDTO;
import SocialFb.Models.Reaction;
import SocialFb.Models.Reply;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    ReplyDTO addReply (Reply reply, long comment_id);

    ReactionDTO addReaction (Reaction reaction, long comment_id);

    PageDTO<CommentDTO> findByPostId (Long id, Pageable pageable);
}
