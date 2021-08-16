package SocialFb.Suppliers;

import SocialFb.Models.Comment;
import SocialFb.Models.Post;
import SocialFb.Repositories.CommentsRepository;
import SocialFb.Repositories.UsersRepository;
import SocialFb.Suppliers.abstraction.AbstractSupplier;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class CommentsSupplier extends AbstractSupplier<Comment> {

    private final ReactionsSupplier reactionsSupplier;
    private final RepliesSupplier repliesSupplier;
    private final CommentsRepository commentsRepository;
    private final UsersRepository usersRepository;



    @Override
    public Set<Comment> supplyMany (int count) {
        Set<Comment> comments = new HashSet<>();
        for ( int i = 0; i < count; i++ ) {
            comments.add(this.supplyOne());
        }
        return commentsRepository.saveAll(comments).stream().collect(Collectors.toSet());
    }

    @Override
    public Comment supplyOne () {
        this.faker = new Faker();
        var users = usersRepository.findAll(Pageable.ofSize(20)).getContent();
        var user = users.get(RandomUtils.nextInt(0, users.size()));

        return Comment.builder()
                .user(user)
                .content(faker.lorem().sentence(RandomUtils.nextInt(1, 100)))
                .reactions(reactionsSupplier.supplyMany(10))
                .replies(repliesSupplier.supplyMany(10))
                .build();
    }

    public List<Comment> supplyMany (int count, Post post) {
        Set<Comment> comments = new HashSet<>();
        for ( int i = 0; i < count; i++ ) {
            var one = this.supplyOne();
            one.setPost(post);
            comments.add(one);
            log.debug("CommentsSupplier supplyMany {} ", one);
        }
        var saved = commentsRepository.saveAll(comments);
        return saved;
    }
}
