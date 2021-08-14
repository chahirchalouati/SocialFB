package SocialFb.Services.Impl;

import SocialFb.DTOs.PageDTO;
import SocialFb.DTOs.PostDTO;
import SocialFb.Mappers.PostsMapper;
import SocialFb.Models.Post;
import SocialFb.Repositories.PostsRepository;
import SocialFb.Requests.PostCreateRequest;
import SocialFb.Requests.PostUpdateRequest;
import SocialFb.Services.CrudBaseOperations;
import SocialFb.Services.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PostsServiceImpl implements CrudBaseOperations<PostDTO, PostCreateRequest, PostUpdateRequest, Post>, PostsService {

    @Override
    public PageDTO<PostDTO> getPageDTO (Page<Post> all, List<PostDTO> content) {
        return CrudBaseOperations.super.getPageDTO(all, content);
    }

    private final PostsRepository postsRepository;

    private final PostsMapper postsMapper;


//    public PageDTO<PostDTO> findAll (Pageable pageable) {
//        return this.postPageSupplier(postsRepository.findAll(pageable));
//    }

    private PageDTO<PostDTO> postPageSupplier (Page<Post> all) {

        return new PageDTO<PostDTO>()
                .setContent(postsMapper.map(all.getContent()))
                .setHasContent(all.hasContent())
                .setHasNext(all.hasNext())
                .setHasPrevious(all.hasPrevious())
                .setNumber(all.getNumber())
                .setNumberOfElements(all.getNumberOfElements())
                .setLast(all.isLast())
                .setSize(all.getSize())
                .setSort(all.getSort())
                .setFirst(all.isFirst())
                .setTotalElements(all.getTotalElements())
                .setTotalPages(all.getTotalPages());
    }

    @Override
    public PostDTO create (PostCreateRequest postCreateRequest) {
        return null;
    }

    @Override
    public Optional<Long> update (PostUpdateRequest postUpdateRequest) {
    return null;
    }

    @Override
    public Optional<Long> delete (Long id) {
        return Optional.empty();
    }

    @Override
    public List<PostDTO> findAll () {
        return postsMapper.map(postsRepository.findAll());
    }

    @Override
    public PageDTO<PostDTO> findAll (Pageable pageable) {
        var all = postsRepository.findAll(pageable);
        var content = postsMapper.map(all.getContent());
        return getPageDTO(all, content);

    }


    @Override
    public Optional<PostDTO> findOne (Long id) {
        return Optional.empty();
    }

    @Override
    public PageDTO<PostDTO> findPostByUserID (Long id, Pageable pageable) {
        var post = postsRepository.findPostByUser_Id(id, pageable);
        return null;
    }


//    public Post save (Post user) {
//        return postsRepository.save(user);
//    }
//
//    public void deleteAll () {
//        postsRepository.deleteAll();
//    }
//
//    public List<Post> saveAll (Set<Post> posts) {
//        return postsRepository.saveAll(posts);
//    }
//
//    public Optional<Post> findById (Long id) {
//        return postsRepository.findById(id);
//    }
//
//    public PageDTO<PostDTO> findByUserId (Long id , Pageable pageable) {
//        return this.postPageSupplier(postsRepository.findPostByUser_Id(id, pageable)) ;
//    }
}
