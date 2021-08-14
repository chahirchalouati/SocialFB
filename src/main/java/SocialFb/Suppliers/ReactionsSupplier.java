package SocialFb.Suppliers;

import SocialFb.Models.Reaction;
import SocialFb.Models.User;
import SocialFb.Repositories.ReactionsRepository;
import SocialFb.Repositories.UsersRepository;
import SocialFb.Suppliers.abstraction.AbstractSupplier;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Component
public class ReactionsSupplier extends AbstractSupplier<Reaction> {

    private final String[] reactionTypes = {"angry", "care", "love", "sad", "like", "haha", "wow"};
    private final ReactionsRepository reactionsRepository;
    private final UsersRepository usersRepository;
    private List<User> users;

    public ReactionsSupplier (ReactionsRepository reactionsRepository, UsersRepository usersRepository) {
        this.usersRepository = usersRepository;

        this.reactionsRepository = reactionsRepository;
    }

    public Set<Reaction> supplyMany (int count) {

        Set<Reaction> reactions = new HashSet<>();
        if ( count > 0 ) {
            for ( int i = 0; i < count; i++ ) {
                reactions.add(this.supplyOne());
            }

        }

        return reactions;
    }

    public Reaction supplyOne () {
        this.users = this.usersRepository.findAll(Pageable.ofSize(50)).getContent();
        return Reaction.builder()
                .type(reactionTypes[RandomUtils.nextInt(0, reactionTypes.length)].toLowerCase(Locale.ROOT))
                .user(this.users.get(RandomUtils.nextInt(0, this.users.size())))
                .build();
    }
}
