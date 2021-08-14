package SocialFb.Services;

import SocialFb.DTOs.PageDTO;
import SocialFb.DTOs.PostDTO;
import org.springframework.data.domain.Pageable;

public interface PostsService {

    PageDTO<PostDTO> findPostByUserID (Long id, Pageable pageable);


}
