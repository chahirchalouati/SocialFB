package SocialFb.DTOs;

import SocialFb.Models.User;
import lombok.Data;

@Data
public class ReactionDTO {
    private long id;
    private String type;
    private User user;
}
