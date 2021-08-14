package SocialFb.Controllers;

import SocialFb.Repositories.PostsRepository;
import SocialFb.Services.CommentService;
import SocialFb.Suppliers.CommentsSupplier;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comments")
@AllArgsConstructor
public class CommentsController {

    private final CommentService commentService;
    private final CommentsSupplier commentsSupplier;
    private final PostsRepository postsRepository;

    @GetMapping
    @Cacheable({"comments"})
    public ResponseEntity<?> findAll (Pageable pageable) throws InterruptedException {
     return new ResponseEntity<>(commentService.findAll(pageable), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @Cacheable(value = "comment.findById", key = "id")
    public ResponseEntity<?> findById (@PathVariable(name = "id") Long id, Pageable pageable) {
        return new ResponseEntity<>(commentService.findByPost_Id(id, pageable), HttpStatus.OK);
    }




}
