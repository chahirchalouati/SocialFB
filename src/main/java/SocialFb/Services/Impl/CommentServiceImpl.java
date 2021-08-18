package SocialFb.Services.Impl;

import SocialFb.DTOs.CommentDTO;
import SocialFb.DTOs.PageDTO;
import SocialFb.DTOs.ReactionDTO;
import SocialFb.DTOs.ReplyDTO;
import SocialFb.Exceptions.CustomEntityNotFoundException;
import SocialFb.Mappers.CommentsMapper;
import SocialFb.Mappers.ReactionMapper;
import SocialFb.Mappers.ReplyMapper;
import SocialFb.Models.Comment;
import SocialFb.Models.Post;
import SocialFb.Repositories.CommentsRepository;
import SocialFb.Repositories.PostsRepository;
import SocialFb.Requests.CommentCreateRequest;
import SocialFb.Requests.CommentUpdateRequest;
import SocialFb.Services.CommentService;
import SocialFb.Services.CrudBaseOperations;
import SocialFb.Services.ReactionService;
import SocialFb.Services.ReplyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CrudBaseOperations<CommentDTO, CommentCreateRequest, CommentUpdateRequest, Comment>, CommentService {

    private final CommentsRepository commentsRepository;
    private final ReplyService repliesService;
    private final ReactionService reactionService;
    private final PostsRepository postsRepository;
    private final CommentsMapper commentsMapper;
    private final ReplyMapper replyMapper;
    private final ReactionMapper reactionMapper;

    @Override
    public CommentDTO create (CommentCreateRequest commentCreateRequest) {
        final Comment comment = this.commentsMapper.commentCreateRequestToComment(commentCreateRequest);
        final Post post = postsRepository.findById(commentCreateRequest.getPost_id())
                .orElseThrow(() -> new CustomEntityNotFoundException("Post with id = " + commentCreateRequest.getPost_id() + " does not exist"));
        comment.setPost(post);
        return this.commentsMapper.commentToCommentDTO(commentsRepository.save(comment));

    }

    @Override
    public Optional<Long> update (CommentUpdateRequest commentUpdateRequest) {
        final Comment comment = this.commentsMapper.CommentUpdateRequestToComment(commentUpdateRequest);
        return Optional.of(commentsRepository.save(comment).getId());
    }

    @Override
    public Optional<Long> delete (Long id) {
        if ( commentsRepository.existsById(id) ) {
            commentsRepository.deleteById(id);
            return Optional.of(id);
        }
        throw new CustomEntityNotFoundException("Comment with id = " + id + " does not exist");
    }

    @Override
    public List<CommentDTO> findAll () {
        return this.commentsMapper.map(commentsRepository.findAll());
    }

    @Override
    public PageDTO<CommentDTO> findAll (Pageable pageable) {
        final Page<Comment> commentPage = this.commentsRepository.findAll(pageable);
        return this.getPageDTO(commentPage, this.commentsMapper.map(commentPage.getContent()));
    }

    @Override
    public Optional<CommentDTO> findById (Long id) {
        if ( commentsRepository.existsById(id) ) {
            return Optional.ofNullable(this.commentsMapper.commentToCommentDTO(this.commentsRepository.findById(id).get()));
        }
        throw new CustomEntityNotFoundException("Comment with id = " + id + " does not exist");
    }

    @Override
    public PageDTO<CommentDTO> getPageDTO (Page<Comment> all, List<CommentDTO> content) {
        return CrudBaseOperations.super.getPageDTO(all, content);
    }


    @Override
    public ReplyDTO addReply (ReplyDTO replyDTO, long comment_id) {
        final Comment comment = this.commentsRepository.findById(comment_id)
                .orElseThrow(() -> new CustomEntityNotFoundException("Comment with id = " + comment_id + " does not exist"));
        comment.getReplies().add(this.replyMapper.replyDTOToReply(replyDTO));
        final Comment saved = this.commentsRepository.save(comment);
        replyDTO.setId(saved.getId());
        return replyDTO;
    }

    @Override
    public ReactionDTO addReaction (ReactionDTO reactionDTO, long comment_id) {
        final Comment comment = this.commentsRepository.findById(comment_id)
                .orElseThrow(() -> new CustomEntityNotFoundException("Comment with id = " + comment_id + " does not exist"));
        comment.getReactions().add(this.reactionMapper.reactionDTOToReaction(reactionDTO));
        final Comment saved = this.commentsRepository.save(comment);
        reactionDTO.setId(saved.getId());
        return reactionDTO;
    }

    @Override
    public PageDTO<CommentDTO> findByPostId (Long id, Pageable pageable) {
        final Page<Comment> pageComments = commentsRepository.findByPost_Id(id, pageable);
        return this.getPageDTO(pageComments, commentsMapper.map(pageComments.getContent()));
    }
}
