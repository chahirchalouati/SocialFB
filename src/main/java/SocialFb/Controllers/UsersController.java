package SocialFb.Controllers;

import SocialFb.DTOs.UserDTO;
import SocialFb.Services.Impl.UsersServiceImpl;
import SocialFb.Suppliers.UsersSupplier;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UsersController {

    private final UsersServiceImpl usersService;
    private final UsersSupplier usersSupplier;

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable) {
      //  usersService.saveAll(usersSupplier.supplyMany(10));
        return new ResponseEntity<>(usersService.findAll(pageable), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        Optional<UserDTO> user = usersService.findOne(id);
        if (user.isEmpty()) {
            return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }
}
