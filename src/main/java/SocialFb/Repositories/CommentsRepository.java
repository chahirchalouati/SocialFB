package SocialFb.Repositories;

import SocialFb.Models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findByPost_Id (Long id, Pageable pageable);
}
