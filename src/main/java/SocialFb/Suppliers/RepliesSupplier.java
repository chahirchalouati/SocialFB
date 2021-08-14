package SocialFb.Suppliers;

import SocialFb.Models.Reply;
import SocialFb.Repositories.RepliesRepository;
import SocialFb.Repositories.UsersRepository;
import SocialFb.Suppliers.abstraction.AbstractSupplier;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class RepliesSupplier extends AbstractSupplier<Reply> {
    private final ReactionsSupplier reactionsSupplier;
    private final RepliesRepository repliesRepository;
    private final UsersRepository  usersRepository;

    @Override
    public Set<Reply> supplyMany (int count) {
        Set<Reply> replies = new HashSet<>();
        if ( count > 0 ) {
            for ( int i = 0; i < count; i++ ) {
                replies.add(this.supplyOne());
            }

        }
        return replies;
    }

    @Override
    public Reply supplyOne () {
        var users = usersRepository.findAll();
        return Reply
                .builder()
                .content(this.faker.lorem().sentence(RandomUtils.nextInt(0, 1024)))
                .reactions(reactionsSupplier.supplyMany(RandomUtils.nextInt(0, 25)))
                .user(users.get(RandomUtils.nextInt(0, users.size())))
                .build();
    }
}
