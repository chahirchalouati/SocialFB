package SocialFb.Mappers;

import SocialFb.DTOs.PostDTO;
import SocialFb.Models.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostsMapper {

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
    PostDTO postToPostDTO (Post post);

    List<PostDTO> map (List<Post> posts);
}
