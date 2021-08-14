package SocialFb.DTOs;

import SocialFb.Models.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PostDTO {

    private String id;
    private String content;
    @JsonProperty("attachments")
    private Set<Attachment> attachments;
    @JsonProperty("comments")
    private Set<Comment> comments;
    @JsonProperty("reactions")
    private Set<Reaction> reactions;
    private UserDTO user;

    private Set<MediaContent> mediaContents;
    private Set<AllowedAction> allowedActions;

    @JsonProperty("createDate")
    @JsonFormat( shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;
}
