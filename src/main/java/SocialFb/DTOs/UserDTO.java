package SocialFb.DTOs;

import SocialFb.Models.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    private String id;
    private String fullName;
    private String email;
    private String userName;
    private Profile profile;
}
