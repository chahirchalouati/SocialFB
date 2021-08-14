package SocialFb.Mappers;

import SocialFb.DTOs.ReactionDTO;
import SocialFb.Models.Reaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReactionsMapper {
    @Mappings({
            @Mapping(target = "user", source = "user"),
            @Mapping(target = "type", source = "type"),
            @Mapping(target = "id", source = "id", ignore = true)
    })
    Reaction reactionDTOToReaction (ReactionDTO reactionDTO);

    @Mappings({
            @Mapping(target = "user", source = "user"),
            @Mapping(target = "type", source = "type"),
            @Mapping(target = "id", source = "id")
    })
    ReactionDTO reactionToReactionDTO (Reaction reaction);

    List<Reaction> mapReactionList (List<ReactionDTO> reactionDTOList);

    List<ReactionDTO> mapReactionDtoList (List<Reaction> reactionList);
}
