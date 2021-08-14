package SocialFb.Suppliers;

import SocialFb.Models.Profile;
import SocialFb.Models.User;
import SocialFb.Repositories.PexeLObjectRepository;
import SocialFb.Repositories.ProfilesRepository;
import SocialFb.Suppliers.abstraction.AbstractSupplier;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class UsersSupplier extends AbstractSupplier<User> {

    private final ProfilesRepository profilesRepository;
    private final ContactSupplier contactSupplier;
    private final PexeLObjectRepository pexeLObjectRepository;


    public Set<User> supplyMany (int count) {

        Set<User> users = new HashSet<>();
        if ( count > 0 ) {
            for ( int i = 0; i < count; i++ ) {
                users.add(this.supplyOne());
            }
        }

        return users;
    }

    public User supplyOne () {
        var objects = pexeLObjectRepository.findAll();
        var x = objects.get(RandomUtils.nextInt(0, objects.size()));


        return User.builder()
                .email(this.faker.internet().emailAddress())
                .firstName(this.faker.name().firstName())
                .lastName(this.faker.name().lastName())
                .password(this.faker.internet().password())
                .userName(this.faker.name().username().replace("\\.", " "))
                .profile(Profile.builder()
                        .avatar_url(x.getSrc().getSmall())
                        .build())
                .contacts(new HashSet<>(contactSupplier.supplyMany(2)))
                .build();
    }
}
