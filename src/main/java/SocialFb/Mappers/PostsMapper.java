package SocialFb.Mappers;

import SocialFb.DTOs.PostDTO;
import SocialFb.Helpers.PostBuildHelper;
import SocialFb.Models.Post;
import SocialFb.Requests.PostCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PostsMapper {
    @Autowired
    PostBuildHelper postBuildHelper;
    @Mappings({
            @Mapping(target = "user", source = "user"),
            @Mapping(target = "user.fullName", expression = "java(user.getFirstName() + \" \" + user.getLastName())"),
            @Mapping(target = "content", source = "content"),
            @Mapping(target = "createdAt", source = "createdAt", dateFormat = "dd-MM-yyyy HH:mm:ss"),
            @Mapping(target = "attachments", source = "attachments"),
            @Mapping(target = "allowedActions", source = "allowedActions"),
            @Mapping(target = "reactions", source = "reactions"),
            @Mapping(target = "mediaContents", ignore = true),
            @Mapping(target = "comments", ignore = true),
    })
    public  abstract PostDTO postToPostDTO (Post post);

    public abstract List<PostDTO> map (List<Post> posts);
    @Mappings({
            @Mapping(target = "content", source = "content"),
            @Mapping(target = "attachments", expression = "java(this.postBuildHelper.getAttachmentSet(this.postBuildHelper.getFileDetails(postCreateRequest)))"),
    })

    public abstract Post  postCreateRequestToPostWithAttachments(PostCreateRequest postCreateRequest);
    @Mappings({
            @Mapping(target = "content", source = "content")
     })
    public abstract Post postCreateRequestToPost(PostCreateRequest postCreateRequest);



}
