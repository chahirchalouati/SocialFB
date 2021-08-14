package SocialFb.Repositories;

import SocialFb.Models.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionsRepository extends JpaRepository<Reaction, Long> {


}
