package SocialFb.Services.Impl;

import SocialFb.DTOs.PageDTO;
import SocialFb.DTOs.UserDTO;
import SocialFb.Mappers.UserMapper;
import SocialFb.Models.User;
import SocialFb.Repositories.UsersRepository;
import SocialFb.Requests.UserCreateRequest;
import SocialFb.Requests.UserUpdateRequest;
import SocialFb.Services.CrudBaseOperations;
import SocialFb.Services.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UsersServiceImpl implements CrudBaseOperations<UserDTO, UserCreateRequest, UserUpdateRequest, User>, UsersService {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;


    @Override
    public UserDTO create (UserCreateRequest userCreateRequest) {
        return null;
    }

    @Override
    public Optional<Long> update (UserUpdateRequest userUpdateRequest) {
        return Optional.empty();
    }

    @Override
    public Optional<Long> delete (Long id) {
        return Optional.empty();
    }

    @Override
    public List<UserDTO> findAll () {
        return null;
    }

    @Override
    public PageDTO<UserDTO> findAll (Pageable pageable) {
        return null;
    }

    @Override
    public Optional<UserDTO> findOne (Long id) {
        return Optional.empty();
    }

    @Override
    public PageDTO<UserDTO> getPageDTO (Page<User> all, List<UserDTO> content) {
        return CrudBaseOperations.super.getPageDTO(all, content);
    }
}
