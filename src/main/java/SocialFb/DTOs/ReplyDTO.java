package SocialFb.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReplyDTO {
    private long id;
    private String content;
    private UserDTO user;
    private Set<ReactionDTO> reactions;
}
