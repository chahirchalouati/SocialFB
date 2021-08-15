package SocialFb.Mappers;

import SocialFb.DTOs.CommentDTO;
import SocialFb.Models.Comment;
import SocialFb.Requests.CommentCreateRequest;
import SocialFb.Requests.CommentUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentsMapper {

    @Mappings({
            @Mapping(target = "user", source = "user"),
            @Mapping(target = "post", source = "post"),
            @Mapping(target = "content", source = "content"),
            @Mapping(target = "createdAt", source = "createdAt", dateFormat = "dd-MM-yyyy HH:mm:ss"),
            @Mapping(target = "replies", source = "replies"),
            @Mapping(target = "reactions", source = "reactions"),
    })
    CommentDTO commentToCommentDTO (Comment comment);

    List<CommentDTO> map (List<Comment> comments);

    @Mappings({
            @Mapping(target = "content", source = "content"),
            @Mapping(target = "post.id", source = "post_id"),
    })
    Comment commentCreateRequestToComment (CommentCreateRequest commentCreateRequest);

    @Mappings({
            @Mapping(target = "content", source = "content"),
            @Mapping(target = "post.id", source = "post_id"),
            @Mapping(target = "replies", source = "replies"),
            @Mapping(target = "reactions", source = "reactions"),
    })
    Comment CommentUpdateRequestToComment (CommentUpdateRequest commentUpdateRequest);



}
