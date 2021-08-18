package SocialFb.Services.Impl;

import SocialFb.DTOs.PageDTO;
import SocialFb.DTOs.ReplyDTO;
import SocialFb.Models.Reply;
import SocialFb.Requests.ReplyCreateRequest;
import SocialFb.Requests.ReplyUpdateRequest;
import SocialFb.Services.CrudBaseOperations;
import SocialFb.Services.ReplyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReplyServiceImpl implements CrudBaseOperations<ReplyDTO, ReplyCreateRequest, ReplyUpdateRequest,Reply>, ReplyService {


    @Override
    public ReplyDTO create (ReplyCreateRequest replyCreateRequest) {
        return null;
    }

    @Override
    public Optional<Long> update (ReplyUpdateRequest replyUpdateRequest) {
        return Optional.empty();
    }

    @Override
    public Optional<Long> delete (Long id) {
        return Optional.empty();
    }

    @Override
    public List<ReplyDTO> findAll () {
        return null;
    }

    @Override
    public PageDTO<ReplyDTO> findAll (Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ReplyDTO> findById (Long id) {
        return Optional.empty();
    }

    @Override
    public PageDTO<ReplyDTO> getPageDTO (Page<Reply> all, List<ReplyDTO> content) {
        return CrudBaseOperations.super.getPageDTO(all, content);
    }
}
