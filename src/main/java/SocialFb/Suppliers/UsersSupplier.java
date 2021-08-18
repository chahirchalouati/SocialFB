package SocialFb.Suppliers;

import SocialFb.Models.Profile;
import SocialFb.Models.User;
import SocialFb.Repositories.PexeLObjectRepository;
import SocialFb.Repositories.ProfilesRepository;
import SocialFb.Repositories.UsersRepository;
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
    private final UsersRepository usersRepository;

    public Set<User> supplyMany (int count) {

        Set<User> users = new HashSet<>();
        if ( count > 0 ) {
            for ( int i = 0; i < count; i++ ) {
                users.add(this.supplyOne());
            }
        }
        usersRepository.saveAll(users);
        return users;
    }

    public User supplyOne () {
//        var objects = pexeLObjectRepository.findAll();
//        var x = objects.get(RandomUtils.nextInt(0, objects.size()));


        return User.builder()
                .email(this.faker.internet().emailAddress())
                .firstName(this.faker.name().firstName())
                .lastName(this.faker.name().lastName())
                .password(this.faker.internet().password())
                .userName(this.faker.name().username().replace("\\.", " "))
                .profile(Profile.builder()
                        .avatar_url("https://images.pexels.com/photos/842711/pexels-photo-842711.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940")
                        .build())
                .contacts(new HashSet<>(contactSupplier.supplyMany(2)))
                .build();
    }
}
