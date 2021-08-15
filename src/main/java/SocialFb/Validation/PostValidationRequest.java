package SocialFb.Validation;

import SocialFb.Exceptions.BadRequestException;
import SocialFb.Requests.PostCreateRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class PostValidationRequest {
    public void validateCreateRequest (PostCreateRequest postCreateRequest) {
        if ( StringUtils.isEmpty(postCreateRequest.getContent()) && CollectionUtils.isEmpty(postCreateRequest.getAttachments()) ) {
            throw new BadRequestException("you must provide at least one of the following args [ content , files]");
        }
    }
}
