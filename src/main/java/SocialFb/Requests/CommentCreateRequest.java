package SocialFb.Requests;

import SocialFb.DTOs.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CommentCreateRequest {
    @NotBlank(message = "post can't be blank")
    private long post_id;
    @NotBlank(message = "content can't be blank")
    private String content;

    private UserDTO user;
}
