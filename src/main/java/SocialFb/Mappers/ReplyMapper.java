package SocialFb.Mappers;

import SocialFb.DTOs.ReplyDTO;
import SocialFb.Models.Reply;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReplyMapper {

    ReplyDTO replyToReplyDTO (Reply reply);

    Reply replyDTOToReply (ReplyDTO replyDTO);


    List<ReplyDTO> mapReplyDtoList (List<Reply> reply);

    List<Reply> mapReplyList (List<ReplyDTO> replyDTO);


}
