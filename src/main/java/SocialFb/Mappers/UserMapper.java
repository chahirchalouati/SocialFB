package SocialFb.Mappers;

import SocialFb.DTOs.UserDTO;
import SocialFb.Models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(target = "fullName", expression = "java(user.getFirstName()+\" \"+ user.getLastName())"),
            @Mapping(target = "userName", expression = "java(user.getUserName().replace(\"\\\\.\",\" \"))")
    })
    UserDTO userToUserDTO (User user);

    List<UserDTO> map (List<User> users);
}
