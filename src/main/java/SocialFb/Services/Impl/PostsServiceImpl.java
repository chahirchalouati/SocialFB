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
import SocialFb.Validation.PostValidationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
@Slf4j
public class PostsServiceImpl implements CrudBaseOperations<PostDTO, PostCreateRequest, PostUpdateRequest, Post>, PostsService {

    private final PostsRepository postsRepository;
    private final PostsMapper postsMapper;
    private final PostValidationRequest postValidationRequest;

    @Override
    public PageDTO<PostDTO> getPageDTO (Page<Post> all, List<PostDTO> content) {
        return CrudBaseOperations.super.getPageDTO(all, content);
    }

    private PageDTO<PostDTO> postPageSupplier (Page<Post> all) {

        return new PageDTO<PostDTO>()
                .setContent(this.postsMapper.map(all.getContent()))
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

        Post post;
        this.postValidationRequest.validateCreateRequest(postCreateRequest);
        if ( this.validateAttachmentsSize(postCreateRequest.getAttachments())) {
            post = this.postsMapper.postCreateRequestToPostWithAttachments(postCreateRequest);
        } else {
            post = this.postsMapper.postCreateRequestToPost(postCreateRequest);
        }
        this.postsRepository.save(post);
        log.info("create post: {}", post);
        return this.postsMapper.postToPostDTO(post);
    }

    private boolean validateAttachmentsSize (MultipartFile[] attachments) {
        return ArrayUtils.isNotEmpty(attachments);

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


}
