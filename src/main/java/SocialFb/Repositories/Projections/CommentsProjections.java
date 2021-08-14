package SocialFb.Repositories.Projections;

import SocialFb.Models.Comment;

import java.util.List;

public interface CommentsProjections {
    List<Comment> getComments(long id);
}
