package SocialFb.Services;

import SocialFb.DTOs.CommentDTO;
import SocialFb.DTOs.PageDTO;
import SocialFb.DTOs.ReactionDTO;
import SocialFb.DTOs.ReplyDTO;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    ReplyDTO addReply (ReplyDTO reply, long comment_id);

    ReactionDTO addReaction (ReactionDTO reaction, long comment_id);

    PageDTO<CommentDTO> findByPostId (Long id, Pageable pageable);
}
