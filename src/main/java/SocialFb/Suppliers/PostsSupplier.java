package SocialFb.Suppliers;

import SocialFb.Models.Post;
import SocialFb.Models.User;
import SocialFb.Repositories.PostsRepository;
import SocialFb.Repositories.UsersRepository;
import SocialFb.Suppliers.abstraction.AbstractSupplier;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
@AllArgsConstructor
public class PostsSupplier extends AbstractSupplier<Post> {


    private final AttachmentSupplier attachmentSupplier;
    private final ReactionsSupplier reactionsSupplier;
    private final CommentsSupplier commentsSupplier;
    private final MediaContentSupplier mediaContentSupplier;
    private final UsersRepository usersRepository;
    private final PostsRepository postsRepository;
    private List<User> users;
    private final AllowedActionSupplier allowedActionSupplier;

    @Override
    public Set<Post> supplyMany (int count) {
        Set<Post> posts = new HashSet<>();
        log.debug("supplyMany {} ", count);
        for ( int i = 0; i < count; i++ ) {
            posts.add(this.supplyOne());
        }
        return posts;
    }

    @Override
    public Post supplyOne () {
        try {
            this.users = usersRepository.findAll(Pageable.ofSize(20)).getContent();
            var user = this.users.get(RandomUtils.nextInt(0, (users.size() - 1)));
            var post = postsRepository.save(
                    Post
                            .builder()
                            .content(faker.lorem().sentence(RandomUtils.nextInt(0, 50)))
                            .user(user)
                            .attachments(attachmentSupplier.supplyMany(RandomUtils.nextInt(0, 25)))
                            .reactions(reactionsSupplier.supplyMany(RandomUtils.nextInt(0, 25)))
                            .attachments(Set.of(attachmentSupplier.supplyOne()))
                            .allowedActions(allowedActionSupplier.supplyMany(10))
                            .build());
            log.debug("PostsSupplier supplyMany {} ", post);
            return post;
        } catch (Exception e) {
            return null;
        }

    }
}
