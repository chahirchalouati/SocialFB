package SocialFb.Controllers;

import SocialFb.DTOs.PostDTO;
import SocialFb.Requests.PostCreateRequest;
import SocialFb.Services.Impl.PostsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("posts")
@AllArgsConstructor
public class PostsController {

    private final PostsServiceImpl postsService;

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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity create ( PostCreateRequest postCreateRequest) {
        return ResponseEntity.ok(this.postsService.create(postCreateRequest));
    }
}
