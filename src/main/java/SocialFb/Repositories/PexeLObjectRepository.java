package SocialFb.Repositories;

import SocialFb.Utils.PexelObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PexeLObjectRepository extends JpaRepository<PexelObject, Long> {

}
