package SocialFb.Services.Impl;

import SocialFb.DTOs.CommentDTO;
import SocialFb.DTOs.PageDTO;
import SocialFb.Exceptions.BadRequestException;
import SocialFb.Mappers.CommentsMapper;
import SocialFb.Models.Comment;
import SocialFb.Repositories.CommentsRepository;
import SocialFb.Repositories.PostsRepository;
import SocialFb.Requests.CreateCommentRequest;
import SocialFb.Requests.UpdateCommentRequest;
import SocialFb.Services.CommentService;
import SocialFb.Services.ReactionService;
import SocialFb.Services.ReplyService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentsRepository commentsRepository;
    private final ReplyService repliesService;
    private final ReactionService reactionService;
    private final PostsRepository postsRepository;
    private final CommentsMapper commentsMapper;


    @Override
    public PageDTO<CommentDTO> findByPost_Id (Long id, Pageable pageable) {
        return this.commentPageSupplier(commentsRepository.findByPost_Id(id, pageable));
    }

    @Override
    public PageDTO<CommentDTO> findAll (Pageable pageable) {
        return this.commentPageSupplier(commentsRepository.findAll(pageable));
    }

    @Override
    public CommentDTO create (CreateCommentRequest createCommentRequest) {
        if ( this.validateCreateCommentRequest(createCommentRequest) ) {
            Comment comment = commentsRepository.save(commentsMapper.createCommentRequestToComment(createCommentRequest));
            return commentsMapper.commentToCommentDTO(comment);
        }
        throw new BadRequestException("");
    }

    private boolean validateCreateCommentRequest (CreateCommentRequest createCommentRequest) {
        return StringUtils.isEmpty(createCommentRequest.getContent()) && StringUtils.isBlank(createCommentRequest.getContent());
    }

    @Override
    public CommentDTO deleteOne (long commentId) {
        return null;
    }

    @Override
    public CommentDTO updateOne (UpdateCommentRequest updateCommentRequest) {
        return null;
    }

    private PageDTO<CommentDTO> commentPageSupplier (Page<Comment> all) {

        return new PageDTO<CommentDTO>()
                .setContent(commentsMapper.map(all.getContent()))
                .setHasContent(all.hasContent())
                .setHasNext(all.hasNext())
                .setHasPrevious(all.hasPrevious())
                .setNumber(all.getNumber())
                .setNumberOfElements(all.getNumberOfElements())
                .setLast(all.isLast())
                .setSize(all.getSize())
                .setSort(all.getSort())
                .setFirst(all.isFirst())
                .setTotalElements(all.getTotalElements())
                .setTotalPages(all.getTotalPages());
    }
}
