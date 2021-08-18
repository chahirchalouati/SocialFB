package SocialFb.Services.Impl;

import SocialFb.DTOs.PageDTO;
import SocialFb.DTOs.ReactionDTO;
import SocialFb.Mappers.ReactionsMapper;
import SocialFb.Models.Reaction;
import SocialFb.Repositories.ReactionsRepository;
import SocialFb.Requests.ReactionCreateRequest;
import SocialFb.Requests.ReactionUpdateRequest;
import SocialFb.Services.CrudBaseOperations;
import SocialFb.Services.ReactionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReactionServiceImpl implements CrudBaseOperations<ReactionDTO, ReactionCreateRequest, ReactionUpdateRequest, Reaction>, ReactionService {

    private final ReactionsMapper reactionsMapper;

    private final ReactionsRepository reactionsRepository;


    @Override
    public ReactionDTO create (ReactionCreateRequest reactionCreateRequest) {
        return null;
    }

    @Override
    public Optional<Long> update (ReactionUpdateRequest reactionUpdateRequest) {
        return Optional.empty();
    }

    @Override
    public Optional<Long> delete (Long id) {
        return Optional.empty();
    }

    @Override
    public List<ReactionDTO> findAll () {
        return null;
    }

    @Override
    public PageDTO<ReactionDTO> findAll (Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ReactionDTO> findById (Long id) {
        return Optional.empty();
    }

    @Override
    public PageDTO<ReactionDTO> getPageDTO (Page<Reaction> all, List<ReactionDTO> content) {
        return CrudBaseOperations.super.getPageDTO(all, content);
    }
}
