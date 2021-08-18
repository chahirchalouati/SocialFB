package SocialFb.Services.Impl;

import SocialFb.DTOs.PageDTO;
import SocialFb.DTOs.UserDTO;
import SocialFb.Exceptions.CustomEntityNotFoundException;
import SocialFb.Mappers.UserMapper;
import SocialFb.Models.User;
import SocialFb.Repositories.UsersRepository;
import SocialFb.Requests.UserCreateRequest;
import SocialFb.Requests.UserUpdateRequest;
import SocialFb.Services.CrudBaseOperations;
import SocialFb.Services.UsersService;
import SocialFb.Suppliers.UsersSupplier;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UsersServiceImpl implements CrudBaseOperations<UserDTO, UserCreateRequest, UserUpdateRequest, User>, UsersService {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;
    private final UsersSupplier usersSupplier;


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
        var user = usersRepository.findById(id)
                .orElseThrow(() -> new CustomEntityNotFoundException(String.format("user with id : %s does not exist", id)));
        usersRepository.delete(user);
        return Optional.of(user.getId());
    }

    @Override
    public List<UserDTO> findAll () {
        return userMapper.map(usersRepository.findAll());
    }

    @Override
    public PageDTO<UserDTO> findAll (Pageable pageable) {
        final Page<User> all = usersRepository.findAll(pageable);
        return this.getPageDTO(all, userMapper.map(all.getContent()));
    }

    @Override
    public Optional<UserDTO> findById (Long id) {
        var user = usersRepository.findById(id)
                .orElseThrow(() -> new CustomEntityNotFoundException(String.format("user with id : %s does not exist", id)));
        return Optional.of(userMapper.userToUserDTO(user));
    }

    @Override
    public PageDTO<UserDTO> getPageDTO (Page<User> all, List<UserDTO> content) {
        return CrudBaseOperations.super.getPageDTO(all, content);
    }
}
