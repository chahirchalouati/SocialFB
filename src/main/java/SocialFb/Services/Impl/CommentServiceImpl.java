package SocialFb.Services.Impl;

import SocialFb.DTOs.CommentDTO;
import SocialFb.DTOs.PageDTO;
import SocialFb.DTOs.ReactionDTO;
import SocialFb.DTOs.ReplyDTO;
import SocialFb.Mappers.CommentsMapper;
import SocialFb.Models.Comment;
import SocialFb.Models.Reaction;
import SocialFb.Models.Reply;
import SocialFb.Repositories.CommentsRepository;
import SocialFb.Repositories.PostsRepository;
import SocialFb.Requests.CommentCreateRequest;
import SocialFb.Requests.CommentUpdateRequest;
import SocialFb.Services.CommentService;
import SocialFb.Services.CrudBaseOperations;
import SocialFb.Services.ReactionService;
import SocialFb.Services.ReplyService;
import SocialFb.Validation.CommentValidation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CrudBaseOperations<CommentDTO, CommentCreateRequest, CommentUpdateRequest,Comment>,CommentService {

    private final CommentsRepository commentsRepository;
    private final ReplyService repliesService;
    private final ReactionService reactionService;
    private final PostsRepository postsRepository;
    private final CommentsMapper commentsMapper;
    private final CommentValidation commentValidation;

    @Override
    public CommentDTO create (CommentCreateRequest commentCreateRequest) {
        this.commentValidation.validateCreateCommentRequest(commentCreateRequest);
        Comment comment = this.commentsMapper.commentCreateRequestToComment(commentCreateRequest);
        return this.commentsMapper.commentToCommentDTO(commentsRepository.save(comment));

    }

    @Override
    public Optional<Long> update (CommentUpdateRequest commentUpdateRequest) {
        return Optional.empty();
    }

    @Override
    public Optional<Long> delete (Long id) {
        return Optional.empty();
    }

    @Override
    public List<CommentDTO> findAll () {
        return null;
    }

    @Override
    public PageDTO<CommentDTO> findAll (Pageable pageable) {
        return null;
    }

    @Override
    public Optional<CommentDTO> findOne (Long id) {
        return Optional.empty();
    }

    @Override
    public PageDTO<CommentDTO> getPageDTO (Page<Comment> all, List<CommentDTO> content) {
        return CrudBaseOperations.super.getPageDTO(all, content);
    }


    @Override
    public ReplyDTO addReply (Reply reply, long comment_id) {
        return null;
    }

    @Override
    public ReactionDTO addReaction (Reaction reaction, long comment_id) {
        return null;
    }

    @Override
    public PageDTO<CommentDTO> findByPostId (Long id, Pageable pageable) {
        var pageComments = commentsRepository.findByPost_Id(id, pageable);
        return this.getPageDTO(pageComments, commentsMapper.map(pageComments.getContent()));
    }
}
