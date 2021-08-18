package SocialFb.Controllers;

import SocialFb.Requests.UserCreateRequest;
import SocialFb.Requests.UserUpdateRequest;
import SocialFb.Services.Impl.UsersServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final UsersServiceImpl usersServiceImpl;

    @GetMapping()
    public ResponseEntity findAll (Pageable pageable) {
        return new ResponseEntity<>(usersServiceImpl.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById (@PathVariable("id") long id) {
        return new ResponseEntity<>(usersServiceImpl.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create (@RequestBody UserCreateRequest userCreateRequest) {
        return new ResponseEntity<>(usersServiceImpl.create(userCreateRequest), HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update (@RequestBody UserUpdateRequest userUpdateRequest, @PathVariable("id") long id) {
        return new ResponseEntity<>(usersServiceImpl.update(userUpdateRequest), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete (@PathVariable("id") long id) {
        return new ResponseEntity<>(usersServiceImpl.delete(id), HttpStatus.OK);
    }
}
