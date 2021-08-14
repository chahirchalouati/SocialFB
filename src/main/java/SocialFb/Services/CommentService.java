package SocialFb.Services;

import SocialFb.DTOs.CommentDTO;
import SocialFb.DTOs.PageDTO;
import SocialFb.Requests.CreateCommentRequest;
import SocialFb.Requests.UpdateCommentRequest;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    PageDTO<CommentDTO> findByPost_Id (Long id, Pageable pageable);

    PageDTO<CommentDTO> findAll (Pageable pageable);

    CommentDTO create (CreateCommentRequest createCommentRequest);

    CommentDTO deleteOne (long commentId);

    CommentDTO updateOne (UpdateCommentRequest updateCommentRequest);
}
