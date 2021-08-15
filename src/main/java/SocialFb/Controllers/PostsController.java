package SocialFb.Controllers;

import SocialFb.DTOs.PostDTO;
import SocialFb.Services.Impl.PostsServiceImpl;
import SocialFb.Suppliers.PostsSupplier;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("posts")
@AllArgsConstructor
public class PostsController {

    private final PostsServiceImpl postsService;

    private final PostsSupplier postsSupplier;

    @GetMapping
    public ResponseEntity<?> findAll (Pageable pageable) {
        return new ResponseEntity(postsService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById (@PathVariable(name = "id") Long id) {
        Optional<PostDTO> post = postsService.findOne(id);
        if ( post.isEmpty() ) {
            return new ResponseEntity(id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(post.get(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findByUserId (@PathVariable(name = "id") Long id, Pageable pageable) {
        return new ResponseEntity(postsService.findPostByUserID(id, pageable), HttpStatus.OK);
    }
}
