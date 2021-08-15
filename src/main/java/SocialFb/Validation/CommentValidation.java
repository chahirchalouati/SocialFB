package SocialFb.Validation;

import SocialFb.Requests.CommentCreateRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CommentValidation {

    public boolean validateCreateCommentRequest (CommentCreateRequest createCommentRequest) {
        return StringUtils.isEmpty(createCommentRequest.getContent()) && StringUtils.isBlank(createCommentRequest.getContent());
    }

}
