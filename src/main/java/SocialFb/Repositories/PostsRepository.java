package SocialFb.Repositories;

import SocialFb.Models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Post, Long> {
    @Override
    Page<Post> findAll (Pageable pageable);

    Page<Post> findPostByUser_Id (Long id, Pageable pageable);

}
