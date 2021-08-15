package SocialFb.Controllers;

import SocialFb.Requests.CommentCreateRequest;
import SocialFb.Requests.CommentUpdateRequest;
import SocialFb.Services.Impl.CommentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comments")
@AllArgsConstructor
public class CommentsController {

    private final CommentServiceImpl commentService;

    @GetMapping
    @Cacheable({"comments"})
    public ResponseEntity<?> findAll (Pageable pageable) throws InterruptedException {
        return new ResponseEntity<>(commentService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById (@PathVariable(name = "id") Long id, Pageable pageable) {
        return new ResponseEntity<>(commentService.findByPostId(id, pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create (@RequestBody CommentCreateRequest commentCreateRequest) {
        return new ResponseEntity<>(commentService.create(commentCreateRequest), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> update (@RequestBody CommentUpdateRequest commentUpdateRequest) {
        return new ResponseEntity<>(commentService.update(commentUpdateRequest), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete (@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(commentService.delete(id), HttpStatus.OK);
    }
}
