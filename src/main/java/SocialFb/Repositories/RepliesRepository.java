package SocialFb.Repositories;

import SocialFb.Models.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepliesRepository extends JpaRepository<Reply, Long> {

}
